package com.luv2code.springboot.thymeleafdemo.dao;



import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
