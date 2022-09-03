package com.jsfw.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.exception.UserException;
import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.UserRepository;
import com.jsfw.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	public List<Tbl_User> findAll(){
		return repository.findAll();
	}
	
	public Tbl_User findByUserName(String username) {
		return repository.findByUsername(username);
	}
	
	public Optional<Tbl_User> findById(Integer id) {
		return repository.findById(id);
	}
	
	public void create(Tbl_User user) {
		repository.save(user);
	}
	
	public void edit(Tbl_User user) {
		repository.save(user);
	}
	
	public void remove(Integer id) {
		repository.deleteById(id);
	}
	
	public Tbl_User findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public boolean checkEmail(String email){
		Tbl_User user = repository.findByEmail(email);
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}
	public Tbl_User findByEmail(String email) {
		return repository.findByEmail(email);
	}


	public Tbl_User findByPhone(String phone) {
		return repository.findByPhone(phone);
	}
}
