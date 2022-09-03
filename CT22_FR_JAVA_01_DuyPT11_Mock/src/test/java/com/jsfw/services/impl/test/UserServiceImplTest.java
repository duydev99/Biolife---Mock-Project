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

import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.UserRepository;
import com.jsfw.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	UserRepository repository;

	@InjectMocks
	UserServiceImpl serviceImpl;

	@Test
	void test_findAll() {
		List<Tbl_User> user = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			user.add(new Tbl_User(i, "user" + i));
		}

		when(repository.findAll()).thenReturn(user);

		List<Tbl_User> user2 = serviceImpl.findAll();

		assertThat(user2.size()).isEqualTo(user.size());

		verify(repository).findAll();

	}

	@Test
	void test_findByUserName() {
		Tbl_User user = new Tbl_User(1, "user");

		when(repository.findByUsername("user")).thenReturn(user);

		Tbl_User user2 = serviceImpl.findByUserName("user");

		assertThat(user2.getName()).isEqualTo(user.getName());

		verify(repository).findByUsername("user");

	}

	@Test
	void test_findById() {

		Optional<Tbl_User> user = Optional.of(new Tbl_User(1, "user"));

		when(repository.findById(1)).thenReturn(user);

		Optional<Tbl_User> user2 = serviceImpl.findById(1);

		assertThat(user2).isEqualTo(user);
		verify(repository).findById(1);
	}

	@Test
	void test_create() {
		Tbl_User user = new Tbl_User(1, "user");

		when(repository.save(user)).thenReturn(user);

		Tbl_User savedUser = repository.save(user);
		assertThat(savedUser.getId()).isNotNull();
	}

	@Test
	void test_edit() {
		Tbl_User user = new Tbl_User(1, "user");
		when(repository.save(user)).thenReturn(user);

		Tbl_User savedUser = repository.save(user);
		Tbl_User user2 = new Tbl_User(1, "user update");

		// providing knowledge
		when(repository.save(user2)).thenReturn(user2);
		Tbl_User editUser = repository.save(user2);
		assertThat(savedUser.getName().equals(editUser.getName()));
	}

	@Test
	void test_remove() {
		Tbl_User user = new Tbl_User(1, "user");
		doNothing().when(repository).deleteById(user.getId());

		serviceImpl.remove(user.getId());

		verify(repository, times(1)).deleteById(user.getId());
	}
	@Test
	void test_findByUsernameAndPassword() {
		Tbl_User user = new Tbl_User(1, "username","password");

		when(repository.findByUsernameAndPassword("username","password")).thenReturn(user);

		Tbl_User user2 = serviceImpl.findByUsernameAndPassword("username","password");

		assertThat(user2).isEqualTo(user);
		verify(repository).findByUsernameAndPassword("username","password");
	}
	@Test
	void test_findByEmail() {
		Tbl_User user = new Tbl_User(1, "emai");

		when(repository.findByEmail("emai")).thenReturn(user);

		Tbl_User user2 = serviceImpl.findByEmail("emai");

		assertThat(user2).isEqualTo(user);
		verify(repository).findByEmail("emai");
	}
	@Test
	void test_checkEmail() {
		Tbl_User user = new Tbl_User(1, "emai");

		when(repository.findByEmail("emai")).thenReturn(user);

		boolean user2 = serviceImpl.checkEmail("emai");

		assertThat(user2).isTrue();
		verify(repository).findByEmail("emai");
	}
	@Test
	void test_findByPhone() {
		Tbl_User user = new Tbl_User(1, "phone");

		when(repository.findByEmail("phone")).thenReturn(user);

		Tbl_User user2 = serviceImpl.findByEmail("phone");

		assertThat(user2).isEqualTo(user);
		verify(repository).findByEmail("phone");
	}
}
