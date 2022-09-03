package com.jsfw.services;

import com.jsfw.models.FogotPasswordCode;

public interface FogotPasswordCodeService {
	FogotPasswordCode createFogotPasswordCode(FogotPasswordCode fogotPasswordCode);
	
	void deleteFogotPasswordCode(FogotPasswordCode fogotPasswordCode);
	
	FogotPasswordCode getFogotPasswordCodeByCode(String code);
	
	boolean checkCodeExpired(FogotPasswordCode fogotPasswordCode);
}
