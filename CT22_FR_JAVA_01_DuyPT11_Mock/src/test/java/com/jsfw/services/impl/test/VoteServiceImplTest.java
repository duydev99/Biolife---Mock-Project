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

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;
import com.jsfw.repositories.VoteRepository;
import com.jsfw.services.impl.VoteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VoteServiceImplTest {
	@Mock
	VoteRepository repository;

	@InjectMocks
	VoteServiceImpl serviceImpl;

	@Test
	void test_create() {
		Tbl_Vote vote = new Tbl_Vote(1, 1);

		when(repository.save(vote)).thenReturn(vote);

		Tbl_Vote savedVote = repository.save(vote);
		assertThat(savedVote.getId()).isNotNull();
	}

	@Test
	void test_edit() {
		Tbl_Vote vote = new Tbl_Vote(1, 1);
		when(repository.save(vote)).thenReturn(vote);

		Tbl_Vote savedVote = repository.save(vote);
		Tbl_Vote vote2 = new Tbl_Vote(1, 2);

		// providing knowledge
		when(repository.save(vote2)).thenReturn(vote2);
		Tbl_Vote editVote = repository.save(vote2);
		assertThat(editVote.getStar()).isEqualTo(2);

	}
	@Test
	void test_findByProductAndUser() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");
		Tbl_User user = new Tbl_User(1,"user");
		when(repository.findTop01ByTblProductAndTblUserOrderByIdDesc(product, user)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findByProductAndUser( product, user);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findByUser() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		
		Tbl_User user = new Tbl_User(1,"user");
		when(repository.findByTblUser(user)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findByUser(  user);


		assertThat(vote2).isEqualTo(vote);
	}
	
	@Test
	void test_findByProduct() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProduct(product)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findByProduct( product);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findBy1Star() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProductAndStar(product,1)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findBy1Star( product);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findBy2Star() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProductAndStar(product,2)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findBy2Star( product);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findBy3Star() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProductAndStar(product,3)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findBy3Star( product);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findBy4Star() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProductAndStar(product,4)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findBy4Star( product);


		assertThat(vote2).isEqualTo(vote);
	}
	
	@Test
	void test_findBy5Star() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		Tbl_Product product = new Tbl_Product(1,"product");

		when(repository.findByTblProductAndStar(product,5)).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findBy5Star( product);


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_findAll() {
		List<Tbl_Vote> vote = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			vote.add(new Tbl_Vote(i, i));
		}
		

		when(repository.findAll()).thenReturn(vote);


		List<Tbl_Vote> vote2 = serviceImpl.findAll();


		assertThat(vote2).isEqualTo(vote);
	}
	@Test
	void test_remove() {
		Tbl_Vote vote = new Tbl_Vote(1,1); 
		doNothing().when(repository).delete(vote);
		 
		serviceImpl.remove(vote);
		
		verify(repository, times(1)).delete(vote); 
	}@Test
	void test_findById() {
		Optional<Tbl_Vote> vote = Optional.of(new Tbl_Vote(1,1));
	

		when(repository.findById(1)).thenReturn(vote);


		Optional<Tbl_Vote> vote2 = Optional.of(serviceImpl.findById(1));


		assertThat(vote2).isEqualTo(vote);
	}
	


}
