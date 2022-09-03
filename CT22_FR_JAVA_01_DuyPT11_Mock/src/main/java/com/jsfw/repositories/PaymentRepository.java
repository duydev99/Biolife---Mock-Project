package com.jsfw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsfw.models.Tbl_Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Tbl_Payment, Integer>{

	Tbl_Payment findByMethod(String name);
}
