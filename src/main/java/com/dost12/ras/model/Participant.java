package com.dost12.ras.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Participant")
public class Participant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2969658059794026367L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean printed = false;
	
	@NotEmpty
	@Column(name="code", unique=true, nullable=false)
	private String code;
	
	@JsonIgnore
	private String firstName ="";
	
	@JsonIgnore
	private String middleName ="";
	
	@JsonIgnore
	private String lastName ="";
	
	private String company;
	
	private String designation;
	
	private String contact;

	private Integer age = 0;
	
	private String gender;
	
	private String status;
	
	private String business;
	
	@Column(name="isVIP")
	private boolean vip = Boolean.FALSE;
	
	@JsonIgnore
	private String email = "";
	
	
	@Transient
	private String completeName;
	
	@Transient
	private String attendsStr;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "voter", orphanRemoval = true)
    private List<Attended> attends = new ArrayList<>();
    

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;
    
    
	public Participant() {		
		this.code = "R12"+UUID.randomUUID().toString().substring(23).toUpperCase();
		vip = Boolean.FALSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getContact() {
		return contact;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompleteName() {
		if(lastName.isEmpty() && firstName.isEmpty())
			return "";
		if(middleName.length() > 0)
			return lastName.toUpperCase()+", "+firstName.toUpperCase()+" "+middleName.substring(0, 1).toUpperCase();
		return lastName.toUpperCase()+", "+firstName.toUpperCase();
	}
	
	public List<Attended> getAttends() {
		return attends;
	}

	public void setAttends(List<Attended> attends) {
		this.attends = attends;
	}

	public String getAttendsStr() {
		String str = "";
		
		String pattern = "MMM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		for(Attended attended : attends)
			str += simpleDateFormat.format(attended.getDate())+" ";
		return str;
	}

	public void setAttendsStr(String attendsStr) {
		this.attendsStr = attendsStr;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}



}
