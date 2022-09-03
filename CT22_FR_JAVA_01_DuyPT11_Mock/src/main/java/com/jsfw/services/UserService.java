package com.jsfw.services;

import java.util.List;
import java.util.Optional;

import com.jsfw.exception.UserException;
import com.jsfw.models.Tbl_User;

public interface UserService {

	public List<Tbl_User> findAll();

	public Tbl_User findByUserName(String username);

	public Optional<Tbl_User> findById(Integer id);

	public void create(Tbl_User user);

	public void edit(Tbl_User user);

	public void remove(Integer id);

	public Tbl_User findByUsernameAndPassword(String username, String password);

	public Tbl_User findByEmail(String email);

	public Tbl_User findByPhone(String phone);

	// trung
	public boolean checkEmail(String email);
}
