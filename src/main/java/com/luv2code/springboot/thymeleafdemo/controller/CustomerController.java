package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/

    @GetMapping("/customers")
    public String helloMethod() {
        return "WElcome to our customer page ";
    }

    //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form"; // This should match the path to your HTML file
    }

    //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // Save the customer to the database
        customerService.saveCustomer(customer);

        // نا لازم تكتب اسم المسار كامل علشان انت عامل [customers/] علي مستوي ال controller كله
        return "redirect:/customers/list"; // Redirect to the list of customers
        // كل المشكلة اللي كانت عندك انك مش واخد بالك من @RequestMapping
        //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/
    }

    //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/


    @GetMapping("/list")
    public String listCustomers(Model model) {
        // Fetch the list of customers from the database
        List<Customer> customers = customerService.getAllCustomers();
        // This will send all customers to hte customer-list html page
        // Take care of the attribute because this will be used in the html page
        model.addAttribute("customers", customers);
        return "customer-list"; // This should match the path to your HTML file
    }
    //      [   علشان اي صفحة تعمل redirect عليها يكون في بدايتها customers/

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(value = "id") int theId, Model model) {
        // you need to get the customer
        Customer customer = customerService.findById(theId);
        // making attribute to pass it to the form
        model.addAttribute("customer", customer);
        //you need to show the form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam(value = "id") int theId) {
        System.out.println(theId);
        // you to get the employee
        customerService.findById(theId);

        // you neeed to delete it from database
        customerService.deleteById(theId);
        return "redirect:/customers/list";
    }


}
