package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.repositories.ManufacturerRepository;
import com.jsfw.services.impl.ManufacturerServiceImpl;
@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceImplTest {
	@Mock
	ManufacturerRepository repository;
	
	@InjectMocks
	ManufacturerServiceImpl serviceImpl;
	@Test
	void test_findAll() {
		List<Tbl_Manufacturer> manufacturer1 = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			manufacturer1.add(new Tbl_Manufacturer("Manufacturer "+i));
		}
		
		when(repository.findAll()).thenReturn(manufacturer1);
		
		List<Tbl_Manufacturer> manufacturer2 = serviceImpl.findAll();
		
		assertThat(manufacturer2.size()).isEqualTo(manufacturer1.size());
		
		verify(repository).findAll();
		
	}
	
	@Test
	void test_findById() {
		Optional<Tbl_Manufacturer> manufacturer1 = Optional.of(new Tbl_Manufacturer(1,"Manufacturer"));
		
		when(repository.findById(1)).thenReturn(manufacturer1);
		
		Optional<Tbl_Manufacturer> manufacturer2 = serviceImpl.findById(1);
		
		assertThat(manufacturer2).isEqualTo(manufacturer1);
		verify(repository).findById(1);
	}
	
	@Test
	void test_findByName() {
		Tbl_Manufacturer manufacturer1 = new Tbl_Manufacturer("Manufacturer");
		
		when(repository.findByName("Manufacturer")).thenReturn(manufacturer1);
		
		Tbl_Manufacturer manufacturer2 = serviceImpl.findByName("Manufacturer");
		
		assertThat(manufacturer2).isEqualTo(manufacturer1);
		verify(repository).findByName("Manufacturer");
		
	}
	@Test
	void test_create() {
		Tbl_Manufacturer manufacturer1 = new Tbl_Manufacturer("Manufacturer");

		when(repository.save(manufacturer1)).thenReturn(manufacturer1);

		Tbl_Manufacturer savedManufacturer = repository.save(manufacturer1);
		assertThat(savedManufacturer.getId()).isNotNull();
		verify(repository).save(manufacturer1);
	}
	@Test
	void test_edit() {
		Tbl_Manufacturer manufacturer1 = new Tbl_Manufacturer(1, "Manufacturer");
		when(repository.save(manufacturer1)).thenReturn(manufacturer1);

		Tbl_Manufacturer savedManufacturer = repository.save(manufacturer1);
		Tbl_Manufacturer manufacturer2 = new Tbl_Manufacturer(1, "Manufacturer update");


	    // providing knowledge
	    when(repository.save(manufacturer2)).thenReturn(manufacturer2);
	    Tbl_Manufacturer editManufacturer = repository.save(manufacturer2);
	    assertThat(savedManufacturer.getName().equals(editManufacturer.getName()));
	    verify(repository).save(manufacturer2);
	    
	}
	
}
