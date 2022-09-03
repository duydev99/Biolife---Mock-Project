package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jsfw.models.FogotPasswordCode;
import com.jsfw.repositories.FogotPasswordCodeReponsitory;
import com.jsfw.services.impl.FogotPasswordCodeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FogotPasswordCodeServiceImplTest {

	@Mock
	FogotPasswordCodeReponsitory fogotPasswordCodeReponsitory;
	
	@InjectMocks
	FogotPasswordCodeServiceImpl fogotPasswordCodeServiceImpl;
	
	@Test
	void test_CreateFogotPasswordCode() {
		FogotPasswordCode fogotPasswordCode = new FogotPasswordCode("trungtmpc01115@gmail.com","jbdakdnasjagsasjasy62sa");
		
		when(fogotPasswordCodeReponsitory.findByEmail("trungtmpc01115@gmail.com")).thenReturn(null);
		when(fogotPasswordCodeReponsitory.save(fogotPasswordCode)).thenReturn(fogotPasswordCode);
		
		FogotPasswordCode fp = fogotPasswordCodeServiceImpl.createFogotPasswordCode(fogotPasswordCode);
		
		assertThat(fp).isEqualTo(fogotPasswordCode);
	}
	
	@Test
	void test_CreateFogotPasswordCodeExist() {
		FogotPasswordCode fogotPasswordCode = new FogotPasswordCode("trungtmpc01115@gmail.com","jbdakdnasjagsasjasy62sa");
		
		//trungtmpc01115@gmail.com đã có trong database
		FogotPasswordCode fogotPasswordCodeExist = new FogotPasswordCode("trungtmpc01115@gmail.com","saassasasasassasswax");
		
		when(fogotPasswordCodeReponsitory.findByEmail("trungtmpc01115@gmail.com")).thenReturn(fogotPasswordCodeExist);
		 
		doNothing().when(fogotPasswordCodeReponsitory).delete(fogotPasswordCodeExist);
		 
		when(fogotPasswordCodeReponsitory.save(fogotPasswordCode)).thenReturn(fogotPasswordCode);
		
		FogotPasswordCode fp = fogotPasswordCodeServiceImpl.createFogotPasswordCode(fogotPasswordCode);
		
		verify(fogotPasswordCodeReponsitory, times(1)).delete(fogotPasswordCodeExist); 
		assertThat(fp).isEqualTo(fogotPasswordCode);
	}

	@Test
	void test_DeleteFogotPasswordCode() {
		FogotPasswordCode fogotPasswordCodeExist = new FogotPasswordCode("trungtmpc01115@gmail.com","saassasasasassasswax");
		 
		doNothing().when(fogotPasswordCodeReponsitory).delete(fogotPasswordCodeExist);
		 
		fogotPasswordCodeServiceImpl.deleteFogotPasswordCode(fogotPasswordCodeExist);
		
		verify(fogotPasswordCodeReponsitory, times(1)).delete(fogotPasswordCodeExist); 
	}
	
	@Test
	void test_GetFogotPasswordCodeByCode() {
		FogotPasswordCode fogotPasswordCode = new FogotPasswordCode("trungtmpc01115@gmail.com","saassasasasassasswax");
		
		when(fogotPasswordCodeReponsitory.findByCode("saassasasasassasswax")).thenReturn(fogotPasswordCode);
		
		FogotPasswordCode fp = fogotPasswordCodeServiceImpl.getFogotPasswordCodeByCode("saassasasasassasswax");
		
		assertThat(fp).isEqualTo(fogotPasswordCode);
	}
    
	
}
	