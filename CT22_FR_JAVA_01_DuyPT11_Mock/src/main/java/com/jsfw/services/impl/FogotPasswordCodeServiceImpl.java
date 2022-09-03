package com.jsfw.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.FogotPasswordCode;
import com.jsfw.repositories.FogotPasswordCodeReponsitory;
import com.jsfw.services.FogotPasswordCodeService;


@Service
public class FogotPasswordCodeServiceImpl implements FogotPasswordCodeService{
	@Autowired
	FogotPasswordCodeReponsitory fogotPasswordCodeReponsitory;
	
	@Override
	public FogotPasswordCode createFogotPasswordCode(FogotPasswordCode fogotPasswordCode) {
		FogotPasswordCode fogotPasswordCodeCheck = fogotPasswordCodeReponsitory.findByEmail(fogotPasswordCode.getEmail());
		if(fogotPasswordCodeCheck != null) {
			fogotPasswordCodeReponsitory.delete(fogotPasswordCodeCheck);
		}
		return fogotPasswordCodeReponsitory.save(fogotPasswordCode);
	}

	@Override
	public void deleteFogotPasswordCode(FogotPasswordCode fogotPasswordCode) {
		fogotPasswordCodeReponsitory.delete(fogotPasswordCode);
	}

	@Override
	public FogotPasswordCode getFogotPasswordCodeByCode(String code) {
		return fogotPasswordCodeReponsitory.findByCode(code);
	}

	@Override
	public boolean checkCodeExpired(FogotPasswordCode confirmationCode) {
		LocalDateTime dateInToken = confirmationCode.getExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();		
		LocalDateTime dateNow = LocalDateTime.now();
		long diffInMinutes = java.time.Duration.between(dateInToken, dateNow).toMinutes();
		
		if(Math.abs(diffInMinutes) < 10) {
			return  true;
		}else {
			fogotPasswordCodeReponsitory.delete(confirmationCode);
			return false;
		}
	}

}
