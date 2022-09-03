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

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.OrderRepository;
import com.jsfw.services.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
	@Mock
	OrderRepository repository;
	
	@InjectMocks
	OrderServiceImpl serviceImpl;
	
	@Test
	void test_findAll() {
		List<Tbl_Order> order = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			order.add(new Tbl_Order(i,"address"+i,i+1000));
		}
		
		when(repository.findAll()).thenReturn(order);
		
		List<Tbl_Order> order2 = serviceImpl.findAll();
		
		assertThat(order2.size()).isEqualTo(order.size());
		
		verify(repository).findAll();
		
	}
	
	@Test
	void test_findById() {
		
		Optional<Tbl_Order> order = Optional.of(new Tbl_Order(1,"address "+1,1));
		when(repository.findById(1)).thenReturn(order);
		
		Optional<Tbl_Order> order2 = Optional.of(serviceImpl.findById(1));
		
		assertThat(order2).isEqualTo(order);
		verify(repository).findById(1);
	}
	
	@Test
	void test_create() {
		Tbl_Order order = new Tbl_Order(1,"address "+1,1);

		when(repository.save(order)).thenReturn(order);

		Tbl_Order savedOrder = repository.save(order);
		assertThat(savedOrder.getId()).isNotNull();
		verify(repository).save(order);
	}
	@Test
	void test_edit() {
		Tbl_Order order = new Tbl_Order(1,"address "+1,1);
		when(repository.save(order)).thenReturn(order);

		Tbl_Order savedOrder = repository.save(order);
		Tbl_Order order2 = new Tbl_Order(1,"address update "+1,1);


	    // providing knowledge
	    when(repository.save(order2)).thenReturn(order2);
	    Tbl_Order editOrder = repository.save(order2);
	    assertThat(savedOrder.getAddress().equals(editOrder.getAddress()));
	    verify(repository).save(order2);
	    
	}
	@Test
	void test_findByUser() {
		Tbl_User user = new Tbl_User(1, "user");
		List<Tbl_Order> order = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			order.add(new Tbl_Order(i,"address"+i,i+1000));
		}
		when(repository.findByTblUserOrderByIdDesc(user)).thenReturn(order);
		List<Tbl_Order> order2 =serviceImpl.findByUser(user);
		
		assertThat(order2).isEqualTo(order);
	}
	
}
