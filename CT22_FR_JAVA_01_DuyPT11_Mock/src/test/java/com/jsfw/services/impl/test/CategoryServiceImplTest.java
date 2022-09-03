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

import com.jsfw.models.Tbl_Category;
import com.jsfw.repositories.CategoryRepository;
import com.jsfw.services.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

	@Mock
	CategoryRepository repository;

	@InjectMocks
	CategoryServiceImpl serviceImpl;

	@Test
	void test_findAll() {
		List<Tbl_Category> categorys1 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			categorys1.add(new Tbl_Category(i, "categorys" + i));
		}

		when(repository.findAll()).thenReturn(categorys1);

		List<Tbl_Category> categorys2 = serviceImpl.findAll();

		assertThat(categorys2.size()).isEqualTo(categorys1.size());

		verify(repository).findAll();

	}

	@Test
	void test_findById() {

		Optional<Tbl_Category> category1 = Optional.of(new Tbl_Category(1, "categorys"));

		when(repository.findById(1)).thenReturn(category1);

		Optional<Tbl_Category> category2 = serviceImpl.findById(1);

		assertThat(category2).isEqualTo(category1);
	}

	@Test
	void test_findByName() {
		Tbl_Category category1 = new Tbl_Category(1, "categorys");

		when(repository.findByName("categorys")).thenReturn(category1);

		Tbl_Category category2 = serviceImpl.findByName("categorys");

		assertThat(category2).isEqualTo(category1);
	}

	@Test
	void test_create() {
		Tbl_Category category1 = new Tbl_Category(1, "categorys");

		when(repository.save(category1)).thenReturn(category1);

		Tbl_Category savedCategory = repository.save(category1);
		assertThat(savedCategory.getId()).isNotNull();
	}

	@Test
	void test_edit() {
		Tbl_Category category1 = new Tbl_Category(1, "categorys");
		when(repository.save(category1)).thenReturn(category1);

		Tbl_Category savedCategory = repository.save(category1);
		Tbl_Category category2 = new Tbl_Category(1, "categorys update");

		// providing knowledge
		when(repository.save(category2)).thenReturn(category2);
		Tbl_Category editCategory = repository.save(category2);
		assertThat(savedCategory.getName().equals(editCategory.getName()));
	}

}
