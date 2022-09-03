package com.jsfw.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jsfw.utils.EncodeUntil;

import net.bytebuddy.utility.RandomString;

@Entity
public class FogotPasswordCode {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String email;
    
    private String code;
 
    private Date expiryDate;
    
	public FogotPasswordCode() {
		super();
	}

	public FogotPasswordCode(String email) {
		this.email = email;
		this.code = EncodeUntil.encoderString(RandomString.make(45));
		this.expiryDate = new Date();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public FogotPasswordCode(String email, String code) {
		super();
		this.email = email;
		this.code = code;
	}

	
}
