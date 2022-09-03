package com.jsfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_User;

@Repository
public interface UserRepository extends JpaRepository<Tbl_User,Integer>{
	Tbl_User findByUsername(String username);
	Tbl_User findByUsernameAndPassword(String username,String password);
	
	//trung
	Tbl_User findByEmail(String email);
	Tbl_User findByPhone(String phone);
}
