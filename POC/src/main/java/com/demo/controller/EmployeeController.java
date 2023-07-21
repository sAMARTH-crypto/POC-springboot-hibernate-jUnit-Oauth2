
package com.demo.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;

import jakarta.persistence.PersistenceContext;

/*  */

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
//
//    @GetMapping("/")
//    public String index() {
//        return "index"; // The template name without the .html extension
//    }

	
	@GetMapping("/getEmps")
	public ResponseEntity<List<Employee>> getAllEmps() {
	    List<Employee> employees = service.getEmployees();
	    return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	
	@GetMapping("/getempbyid/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id) {
	    Employee employee = service.getEmployeeByID(id);
	    if (employee != null) {
	        return new ResponseEntity<>(employee, HttpStatus.OK);
	    } else {
	        // If the employee with the given ID is not found, return a 404 Not Found response
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	@PostMapping("/addemp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
	    Employee savedEmployee = service.saveEmployee(employee);
	    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}


	
	@PostMapping("/addemps")
	public ResponseEntity<List<Employee>> addEmps(@RequestBody List<Employee> employees) {
	    List<Employee> savedEmployees = service.saveEmployees(employees);
	    if (!savedEmployees.isEmpty()) {
	        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
	    } else {
	        // If no employees were saved, return a 400 Bad Request response
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}

	

	@PutMapping("/updateemp")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
	    Employee updatedEmployee = service.updateEmployee(employee);
	    
	        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	    
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delEmp(@PathVariable int id) {
	    String result = service.deleteRecord(id);
	    
	        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	   
	}

	

	
/*	
	
	@GetMapping("/getEmps")
	public List<Employee> getAllEmps(){
		return service.getEmployees();
	}
	
	@GetMapping("/getempbyid/{id}")
	public Employee getEmpById(@PathVariable int id) {
		return service.getEmployeeByID(id);
	}
	
	
	

	@PostMapping("/addemp")
	public Employee addEmp(@RequestBody Employee employee) {
		
		return service.saveEmployee(employee);
		
	}
	
	

	@PostMapping("/addemps")
	public List<Employee> addEmps(@RequestBody List<Employee> employee) {
		return service.saveEmployees(employee);
	}
	
	@PutMapping("/updateemp")
	public Employee updateEmp(@RequestBody Employee employee) {
		
		return service.updateEmployee(employee);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delEmp(@PathVariable int id) {
		return service.deleteRecord(id);
	}

	*/
}
