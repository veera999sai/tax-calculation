package com.task.taxcalculation.service;

import com.task.taxcalculation.model.Employee;
import com.task.taxcalculation.model.EmployeeTaxResponse;

public interface EmployeeService {
	EmployeeTaxResponse calculateEmployeeTax(Long empId);

	Employee saveEmployeeDetails(Employee employee);
}
