package com.dost12.ras.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dost12.ras.model.Attended;
import com.dost12.ras.model.EventDate;
import com.dost12.ras.model.Participant;
import com.dost12.ras.service.AttendedService;
import com.dost12.ras.service.EventDateService;
import com.dost12.ras.service.UserProfileService;
import com.dost12.ras.service.UserService;
import com.dost12.ras.service.ParticipantService;
import com.dost12.ras.util.JasperReportUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@RequestMapping("/")
public class RecordController {

	@Autowired
	UserService userService;

	
	@Autowired
	ParticipantService participantService;
	
	@Autowired
	AttendedService attendedService;
	
	
	@Autowired
	EventDateService eventDateService;

	
	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	 private static List<String> businessLines = Arrays.asList("OTHERS","MSME", "NGO", "MEDIA", "STUDENT", "NGA", "ACADEME", "LGU");
	
	 
	private JasperReportUtil jrdao = new JasperReportUtil();
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "","/listparticipants" }, method = RequestMethod.GET)
	public String listParticipants(ModelMap model) {
		List<Participant> voters = participantService.findAllVoters();

		model.addAttribute("voters", voters);
		model.addAttribute("events", eventDateService.findAllEventDates());
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "personslist";
	}
	
	@RequestMapping(value = { "/liststatus" }, method = RequestMethod.GET)
	public String reportsData(ModelMap model) {

		String dateStr = "";
		if(getEnableEvent()!= null)
			dateStr = formatter.format(getEnableEvent());
		
		model.addAttribute("currentDate", dateStr);
		model.addAttribute("participantsLGU", attendedService.findAllAttendeds(dateStr, businessLines.get(7)).size());
		model.addAttribute("participantsACADEME", attendedService.findAllAttendeds(dateStr, businessLines.get(6)).size() );
		model.addAttribute("participantsNGA", attendedService.findAllAttendeds(dateStr, businessLines.get(5)).size());
		model.addAttribute("participantsSTUDENT", attendedService.findAllAttendeds(dateStr, businessLines.get(4)).size());
		model.addAttribute("participantsMEDIA", attendedService.findAllAttendeds(dateStr, businessLines.get(3)).size());
		model.addAttribute("participantsNGO", attendedService.findAllAttendeds(dateStr, businessLines.get(2)).size());
		model.addAttribute("participantsMSME", attendedService.findAllAttendeds(dateStr, businessLines.get(1)).size());
		model.addAttribute("participantsOTHERS", attendedService.findAllAttendeds(dateStr, businessLines.get(0)).size());
		
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "statuslist";
	}
	
	
	@RequestMapping("/json/data/listparticipants")
	@ResponseBody
	public List<Participant> getVotes() {
		return participantService.findAllVoters();
	}


	private Date getEnableEvent() {
		List<EventDate> events = eventDateService.findAllEnableEventDates();
		if(!events.isEmpty())
			return events.get(events.size()-1).getDate();
		return null;
	}
	
	@RequestMapping(value = { "/mark-participant-{id}" }, method = RequestMethod.GET)
	public String markAttended(@PathVariable String id, ModelMap model,HttpServletRequest request) {

		Participant voter = participantService.findById(Long.parseLong(id));
		
		Attended attended = new Attended();
		Date dateEvent = getEnableEvent();
		if(dateEvent != null)
			attended.setDate(dateEvent);
	
		attended.setVoter(voter);
		
		attendedService.saveAttended(attended);
		
		voter.getAttends().add(attended);
	
		participantService.updateVoter(voter);
		
		model.addAttribute("events", eventDateService.findAllEventDates());
		model.addAttribute("loggedinuser", getPrincipal());
		//return "personslist";
		return "redirect:/";
	}
	
	

	@RequestMapping(value = { "/select-event" }, method = RequestMethod.GET)
	public String selectEvent(@RequestParam(name = "selecteventid") String selecteventid, ModelMap model, HttpServletRequest request) {

		EventDate eventDate = eventDateService.findById(Integer.parseInt(selecteventid));
		List<EventDate> eventDates = eventDateService.findAllEventDates();
		
		// reset nalang
		for(EventDate e : eventDateService.findAllEventDates()) {
			e.setEnable(false);
			eventDateService.updateEventDate(e);
		}
		
		eventDate.setEnable(true);
		eventDateService.updateEventDate(eventDate);
		
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/";
	}
		
	

	@RequestMapping(value = { "/generate-participantsform" }, method = RequestMethod.GET)
	public String generateReportRegistrationForm(
			@RequestParam(name = "generateNo") String generateNo, ModelMap model,
			HttpServletRequest request) {

		if (Integer.parseInt(generateNo) > 0) {
			for (int i = 0; i < Integer.parseInt(generateNo); i++) {
				Participant code = new Participant();
				participantService.saveVoter(code);		
			}
		}
		model.addAttribute("loggedinuser", getPrincipal());

		return "redirect:/listparticipants";
	}
	
	@RequestMapping(value = "/pdf-participantsform")
	public String pdfReportRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException, NamingException {

		Long generateStartNo = Long.parseLong(request.getParameter("generateStartNo"));
		Long generateEndNo = Long.parseLong(request.getParameter("generateEndNo"));	
		boolean isWalking= Boolean.parseBoolean(request.getParameter("isWalking")); 
		
		String reportFileName = "invitee_form";
		if(isWalking)
			reportFileName = "walkin";
		

		HashMap<String, Object> hmParams = new HashMap<String, Object>();
		
		BufferedImage image1 =  null;
		
		try {
			 File initialImage = new File(request.getSession().getServletContext().getRealPath("/jasper/dost.png"));
			 image1 = ImageIO.read(initialImage);
		}catch(Exception e) {}
		
		hmParams.put("dost", image1);

		JasperReport jasperReport = jrdao.getCompiledFile(reportFileName, request);

		List<Map<String, ?>> listCodes = new ArrayList<Map<String, ?>>();
		

		Map<String, Object> m = null;
		if(generateStartNo > 0 && generateStartNo <= generateEndNo)
		for(Participant participant : participantService.findAllVoters(generateStartNo, generateEndNo)) {
				m = new HashMap<String, Object>();
				
				m.put("id", String.valueOf(participant.getId()));
				m.put("number", participant.getCode() );
				
				if(!isWalking) {
					m.put("lastName", participant.getLastName().toUpperCase());
					m.put("middleName", participant.getMiddleName().toUpperCase());
					m.put("firstName", participant.getFirstName().toUpperCase());
					m.put("office", participant.getCompany());
					m.put("designation", participant.getDesignation());
					m.put("contactNo", participant.getContact());
					m.put("emailAdd", participant.getEmail());
					m.put("age", String.valueOf(participant.getAge()));
					m.put("sex", participant.getGender());
					m.put("status", participant.getStatus());
					m.put("business", participant.getBusiness());
				}
				listCodes.add(m);
		}
		
		
		try {
			jrdao.generateReportPDF(response, hmParams, listCodes, jasperReport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // For
			// PDF
			// report

		return null;
	}
	
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newperson" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		Participant voter = new Participant();
		
		model.addAttribute("voter", voter);
		model.addAttribute("edit", false);
		model.addAttribute("genders", Arrays.asList("Male","Famale"));
		model.addAttribute("businessLines", businessLines);
		model.addAttribute("loggedinuser", getPrincipal());		
		return "person";
	}
	
	@RequestMapping(value = { "/edit-person-{id}" }, method = RequestMethod.GET)
	public String editPerson(@PathVariable String id, ModelMap model) {

		Participant voter = participantService.findById(Long.parseLong(id));
		
		model.addAttribute("voter", voter);
		model.addAttribute("edit", true);
		model.addAttribute("genders", Arrays.asList("Male","Famale"));
		model.addAttribute("businessLines", businessLines);
		model.addAttribute("loggedinuser", getPrincipal());
		return "person";
	}

	
	@RequestMapping(value = { "/newperson" }, method = RequestMethod.POST)
	public String saveUser(@Valid Participant voter, BindingResult result, ModelMap model) {

		String name = "";

		participantService.saveVoter(voter);
		
		model.addAttribute("success", "Code " + voter.getFirstName() + " " + voter.getLastName() + " added record successfully");

		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "personsuccess";

	}
	


	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-person-{id}" }, method = RequestMethod.POST)
	public String updatePerson(@Valid Participant voter, BindingResult result, ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "person";
		}


		participantService.updateVoter(voter);
		model.addAttribute("success",
				"User " + voter.getFirstName() + " " + voter.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "personsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-person-{id}" }, method = RequestMethod.GET)
	public String deletePerson(@PathVariable String id) {
		Participant code = participantService.findById(Long.parseLong(id));
		participantService.deleteVoter(code);
		return "redirect:/listparticipants";
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}
