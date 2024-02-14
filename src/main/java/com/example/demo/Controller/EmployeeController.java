package com.example.demo.Controller;


import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(value = "/save")
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping(value = "/get")
    public List<Employee> get(){
        return employeeRepository.findAll();
    }

    @DeleteMapping(value = "/delete/{id}")
    public  String delete(@PathVariable long id){
        employeeRepository.deleteById(id);
        return "Employee\n" +id+ "\nDeleted";
    }

    @PutMapping(value = "/put/{id}")
    public String put(@RequestBody Employee employee,@PathVariable long id){
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    if(employeeOptional.isPresent()){
        //employee.setId(id);
        employeeRepository.saveAndFlush(employee);
    }
     return " Employee\n\n" +employee+ "\n\nUpdated Succesfully";
    }
}
