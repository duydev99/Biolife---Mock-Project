package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Order_Detail;
import com.jsfw.repositories.OrderDetailRepository;
import com.jsfw.services.impl.OrderDetailServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceImplTest {
	@Mock
	OrderDetailRepository repository;

	@InjectMocks
	OrderDetailServiceImpl serviceImpl;

	@Test
	void test_findAll() {
		List<Tbl_Order_Detail> orderDetail = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			orderDetail.add(new Tbl_Order_Detail(i, i + 100, i + 10000));
		}

		when(repository.findAll()).thenReturn(orderDetail);

		List<Tbl_Order_Detail> orderDetail2 = serviceImpl.findAll();

		assertThat(orderDetail2.size()).isEqualTo(orderDetail.size());

		verify(repository).findAll();

	}

	@Test
	void test_findByOrder() {
		Tbl_Order order = new Tbl_Order(1, "address " + 1, 1 + 10000);
		List<Tbl_Order_Detail> orderDetail = new ArrayList<>();
		when(repository.findByTblOrder(order)).thenReturn(orderDetail);

		List<Tbl_Order_Detail> orderDetail2 = serviceImpl.findByOrder(order);

		assertThat(orderDetail2).isNotNull();
		verify(repository).findByTblOrder(order);
	}

	@Test
	void test_create() {
		Tbl_Order_Detail orderDetail = new Tbl_Order_Detail(1,100,1000);

		when(repository.save(orderDetail)).thenReturn(orderDetail);

		Tbl_Order_Detail savedOrderDetail = repository.save(orderDetail);
		assertThat(savedOrderDetail.getId()).isNotNull();
		verify(repository).save(savedOrderDetail);
	}

}
