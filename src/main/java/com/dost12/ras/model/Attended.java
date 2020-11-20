package com.dost12.ras.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Attended implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 831344718889559603L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date date;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="participant_id", updatable = false, nullable = false)
    @JsonIgnore
    private Participant participant;
    
   

	public Attended() {
		super();
		date = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}




}
