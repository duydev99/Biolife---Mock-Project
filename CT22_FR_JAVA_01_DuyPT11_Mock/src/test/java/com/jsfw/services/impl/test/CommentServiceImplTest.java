package com.jsfw.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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

import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Product;
import com.jsfw.repositories.CommentRepository;
import com.jsfw.services.impl.CommentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
	@Mock
	CommentRepository repository;
	@InjectMocks
	CommentServiceImpl serviceImpl;
	@Test
	void test_create() {
		Tbl_Comment comment = new Tbl_Comment("comment");

		when(repository.save(comment)).thenReturn(comment);

		Tbl_Comment savedComment = repository.save(comment);
		assertThat(savedComment.getId()).isNotNull();
	}
	@Test
	void test_findAll() {
		List<Tbl_Comment> comment1 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			comment1.add(new Tbl_Comment("Ngon " + i));
		}

		when(repository.findAll()).thenReturn(comment1);

		List<Tbl_Comment> comment2 = serviceImpl.findAll();

		assertThat(comment2.size()).isEqualTo(comment1.size());

		verify(repository).findAll();

	}

	@Test
	void test_findById() {
		Optional<Tbl_Comment> comment1 = Optional.of(new Tbl_Comment("ngon, bo, re"));

		when(repository.findById(1)).thenReturn(comment1);

		Optional<Tbl_Comment> comment2 = Optional.of(serviceImpl.findById(1));

		assertThat(comment2).isEqualTo(comment1);
	}
	
	@Test
	void test_edit() {
		Tbl_Comment comment1 = new Tbl_Comment("comment1");
		when(repository.save(comment1)).thenReturn(comment1);

		Tbl_Comment savedComment = repository.save(comment1);
		Tbl_Comment comment2 = new Tbl_Comment("comment update");


	    // providing knowledge
	    when(repository.save(comment2)).thenReturn(comment2);
	    Tbl_Comment editComment = repository.save(comment2);
	    assertThat(savedComment.getContent().equals(editComment.getContent()));

	}
	@Test
	void test_remove() {
		Tbl_Comment comment = new Tbl_Comment(1,"comment"); 
		doNothing().when(repository).delete(comment);
		 
		serviceImpl.remove(comment);
		
		verify(repository, times(1)).delete(comment); 
	}
	@Test
	void test_findByProduct() {
		List<Tbl_Comment> comment1 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			comment1.add(new Tbl_Comment("Ngon " + i));
		}
		Tbl_Product product = new Tbl_Product(1, "product");
		
		when(repository.findByTblProductOrderByCreateTimeDesc(product)).thenReturn(comment1);
		List<Tbl_Comment> comment2 = serviceImpl.findByProduct(product);
		assertThat(comment2).isNotNull();
	}

}
