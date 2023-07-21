package com.demo.service;

import com.demo.dao.EmployeeDAO;
import com.demo.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/* now making use of the DAO object and calling all the methods */

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO erepo;
	
	//for saving single record
	public Employee saveEmployee(Employee employee) { 
		return erepo.save(employee);
	}
	
	// for saving multiple records
	public List<Employee> saveEmployees(List<Employee> employees) { 
		return erepo.saveAll(employees);
	}
	
	//for retrieving multiple records
	public List<Employee> getEmployees(){
		return erepo.findAll();
	}
	
	//for retrieving records byID if record is not present return null
	public Employee getEmployeeByID(int id) {
		return erepo.findById(id).orElse(null);
	}
	
	
	//delete record
	
	public String deleteRecord(int id) {
		erepo.deleteById(id);
		return "Record deleted succesfully!" + id;
	}
	
	public Employee updateEmployee(Employee employee) {
		Employee existEmp = erepo.findById(employee.getId()).orElse(null);
		
		existEmp.setFirstName(employee.getFirstName());
		existEmp.setLastName(employee.getLastName());
		existEmp.setDepart(employee.getDepart());
		existEmp.setDesig(employee.getDesig());
		existEmp.setAge(employee.getAge());
		return erepo.save(existEmp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
