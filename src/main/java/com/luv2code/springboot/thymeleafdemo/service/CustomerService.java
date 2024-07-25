package com.luv2code.springboot.thymeleafdemo.service;


import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService  {


    List<Customer> getAllCustomers();

    Customer findById(int theId);

    Customer saveCustomer(Customer theCustomer);

    void deleteById(int theId);

}
