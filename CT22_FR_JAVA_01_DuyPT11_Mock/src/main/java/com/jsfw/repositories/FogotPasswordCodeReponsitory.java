package com.jsfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsfw.models.FogotPasswordCode;

public interface FogotPasswordCodeReponsitory extends JpaRepository<FogotPasswordCode, Long>{
	FogotPasswordCode findByCode(String code);
	
	FogotPasswordCode findByEmail(String email);
	
}