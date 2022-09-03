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

import com.jsfw.models.Tbl_Payment;
import com.jsfw.repositories.PaymentRepository;
import com.jsfw.services.impl.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
	@Mock
	PaymentRepository repository;
	
	@InjectMocks
	PaymentServiceImpl serviceImpl;
	
	@Test
	void test_findAll() {
		List<Tbl_Payment> payment = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			payment.add(new Tbl_Payment(i,"method"+i));
		}
		
		when(repository.findAll()).thenReturn(payment);
		
		List<Tbl_Payment> payment2 = serviceImpl.findAll();
		
		assertThat(payment2.size()).isEqualTo(payment.size());
		
		verify(repository).findAll();
		
	}
	
	@Test
	void test_findById() {
		
		Optional<Tbl_Payment> payment = Optional.of(new Tbl_Payment(1,"modthod"));
		
		when(repository.findById(1)).thenReturn(payment);
		
		Optional<Tbl_Payment> payment2 = serviceImpl.findById(1);
		
		assertThat(payment2).isEqualTo(payment);
	}
	
	@Test
	void test_findByName() {
		Tbl_Payment payment = new Tbl_Payment(1,"method");
		
		when(repository.findByMethod("method")).thenReturn(payment);
		
		Tbl_Payment payment2 = serviceImpl.findByName("method");
		
		assertThat(payment2).isEqualTo(payment);
	}@Test
	void test_create() {
		Tbl_Payment payment = new Tbl_Payment(1,"modthod ");

		when(repository.save(payment)).thenReturn(payment);

		Tbl_Payment savedOrder = repository.save(payment);
		assertThat(savedOrder.getId()).isNotNull();
		verify(repository).save(payment);
	}
	@Test
	void test_edit() {
		Tbl_Payment payment = new Tbl_Payment(1,"modthod ");
		when(repository.save(payment)).thenReturn(payment);

		Tbl_Payment savedPayment = repository.save(payment);
		Tbl_Payment payment2 = new Tbl_Payment(1,"modthod update ");


	    // providing knowledge
	    when(repository.save(payment2)).thenReturn(payment2);
	    Tbl_Payment editPayment = repository.save(payment2);
	    assertThat(savedPayment.getMethod().equals(editPayment.getMethod()));
	    verify(repository).save(payment2);
	    
	}
}
