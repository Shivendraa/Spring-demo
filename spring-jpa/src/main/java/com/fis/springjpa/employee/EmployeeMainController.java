package com.fis.springjpa.employee;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/employees") // This means URL's start with /demo (after Application path)
public class EmployeeMainController {
  @Autowired // This means to get the bean called employeeRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private EmployeeRepository employeeRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public String addNewUser (@RequestBody Employee employee) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

//    Employee n = new Employee();
//    n.setName(name);
//    n.setSalary(salary);
    employeeRepository.save(employee);
    return "Saved employee details";
  }

  @GetMapping(path="/all")
  public Iterable<Employee> getAllUsers() {
    // This returns a JSON or XML with the users
    Iterable<Employee> itr = employeeRepository.findAll();
    java.util.List<Employee> list = new ArrayList<>();
    itr.forEach(list::add);
    if(!list.isEmpty()) {
        return itr;
    }
    else {
    	return itr;
    }
  }
  
  @PutMapping(path="/edit/{id}")
  public String updateUser(@RequestBody Employee employee) {
	  employeeRepository.save(employee);
	  return "Updated employee details";
  }
  
  @DeleteMapping(path="delete/{id}")
  public String deleteUser(@PathVariable Integer id) {
	  Optional<Employee> employee = employeeRepository.findById(id);
	  if(employee.isPresent()) {
		  employeeRepository.delete(employee.get());
		  return "Employee with id "+id+" is deleted";
	  }
	  else
		  return "Employee with id "+id+" not found";
  }

  @GetMapping(path="indi/{id}")
  public Employee getEmployee(@PathVariable Integer id) {
	  Optional<Employee> employee = employeeRepository.findById(id);
	  return employee.get();
  }
}
