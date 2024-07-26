package com.task.taxcalculation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.taxcalculation.model.Employee;
import com.task.taxcalculation.model.EmployeeTaxResponse;
import com.task.taxcalculation.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeTaxService;

	@PostMapping("/saveEmployeeDetails")
	public ResponseEntity<?> saveEmployeeDetails(@RequestBody Employee employee) {
		Employee createdEmployee = employeeTaxService.saveEmployeeDetails(employee);
		return new ResponseEntity<>(createdEmployee.getEmployeeId(), HttpStatus.OK);
	}

	/**
	 * example request: { "empId":2 }
	 * 
	 * @param payload
	 * @return
	 */
	@PostMapping("/getEmployeeTaxDeduction")
	public ResponseEntity<EmployeeTaxResponse> getEmployeeTaxDeduction(@RequestBody Map<String, Object> payload) {
		Long employeeId = ((Number) payload.get("empId")).longValue();
		EmployeeTaxResponse taxResponse = employeeTaxService.calculateEmployeeTax(employeeId);
		return new ResponseEntity<>(taxResponse, HttpStatus.OK);
	}

}
