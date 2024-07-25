package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

// if you will return normal json object you need to @RestController ==> Without MVC
// if you will return something to the view you need use @Controller ==> MVC
// @Controller will require model الشنطة Model
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    // we need to use constructor to make autoWiring
    @Autowired
    EmployeeController(EmployeeService theEmployeeService, CustomerService customerService) {
        this.employeeService = theEmployeeService;
    }
//
//    @GetMapping("/employees")
//    public String hello() {
//        return "hello-page";
//    }
//
//    @GetMapping("/")
//    public String helloApi() {
//        return "hello-page";
//    }

    // we need to add some mapping to list employees
    @GetMapping("/list")
    public String getAllEmployees(Model model) {
        // you need to get the employees from database
        List<Employee> emplList = employeeService.findAll();
        // then you need to add attribute to display to the information
        model.addAttribute("employees", emplList);

        // you need to give the html page name
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        // we need to attribute called model to add some items
        model.addAttribute("employee", employee);

        // so here we need to pass the
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        // we need to save the employee
        employeeService.save(employee);
        return "redirect:/employees/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(value = "id") int theId, Model model) {
        // get the
        // هنجهز object علشان نشيل فيه المعلومات اللي جاية من ال form
        Employee employee = employeeService.findById(theId);
        // you need to repopulate the form
        model.addAttribute("employee", employee);
        //  هتضيف ال attribute  الجديد لل form علشان نعدل عليه
        return "employees/employee-form";
        // You must make simple trick in the form
        // هتحط Hidden btn علشان يشاور علي ال id
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(value = "id") int theId) {
        // you need to get id from table
        Employee employee = employeeService.findById(theId);
        // we need to delete this one from database
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }


//    @GetMapping("/edit")
//    public String editEmployee(@RequestParam(value = "id") int id, Model model) {
//        //we need to get the employee from database base on the id
//        Employee employee = employeeService.findById(id);
//        //you need to add another attribute
//        model.addAttribute("updateEmployee", employee);
//        //we need to return back to the list
//        return "employees/employee-form";
//    }
//
//    @PostMapping("/update")
//    public String updateEmployee(Employee employee) {
//        employeeService.save(employee);
//        return "redirect:/employees/list";  // you to go to the method in the Controller
//    }
}
