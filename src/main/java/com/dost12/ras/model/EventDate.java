
package com.dost12.ras.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class EventDate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358721163337443521L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date date;
    
    @Transient
    private String dateStr;
    
    private Boolean enable = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getDateStr() {
		String str = "";

		if(date != null) {
			String pattern = "MMM dd, YYYY";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(date);
		}
		return str;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	
	
}
