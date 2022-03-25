package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRep;

@RestController
@RequestMapping()
public class EmployeeController {
	
	@Autowired
	private EmployeeRep employeeRep;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		
		List<Employee> allEmployeeList = employeeRep.findAll();
		return allEmployeeList;
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeRep.findById(employeeId).get();
		
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRep.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRep.findById(employeeId).get();
		
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		final Employee updatedEmployee = employeeRep.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeRep.findById(employeeId).get();
		
		employeeRep.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
