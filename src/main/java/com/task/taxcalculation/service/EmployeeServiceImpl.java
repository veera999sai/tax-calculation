package com.task.taxcalculation.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taxcalculation.model.Employee;
import com.task.taxcalculation.model.EmployeeTaxResponse;
import com.task.taxcalculation.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final double TAX_RATE_5_PERCENT = 0.05;
	private static final double TAX_RATE_10_PERCENT = 0.10;
	private static final double TAX_RATE_20_PERCENT = 0.20;
	private static final double CESS_RATE = 0.02;

	public EmployeeTaxResponse calculateEmployeeTax(Long empId) {

		Employee employee = employeeRepository.findById(empId).orElse(null);

		double yearlySalary = calculateSalaryByDOJ(employee.getSalary(), employee.getDateOfJoining());
		double taxAmount = calculateTaxAmount(yearlySalary);

		double cessAmount = yearlySalary * CESS_RATE;
		double totalTaxDeduction = taxAmount + cessAmount;

		EmployeeTaxResponse response = new EmployeeTaxResponse();
		response.setEmployeeId(employee.getEmployeeId());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setYearlySalary(yearlySalary);
		response.setTaxAmount(taxAmount);
		response.setCessAmount(cessAmount);
		response.setTotalTaxDeduction(totalTaxDeduction);

		return response;
	}

	private double calculateTaxAmount(double yearlySalary) {
		if (yearlySalary <= 250000) {
			return 0;
		} else if (yearlySalary <= 500000) {
			return (yearlySalary - 250000) * TAX_RATE_5_PERCENT;
		} else if (yearlySalary <= 1000000) {
			return (250000 * TAX_RATE_5_PERCENT) + (yearlySalary - 500000) * TAX_RATE_10_PERCENT;
		} else {
			return (250000 * TAX_RATE_5_PERCENT) + (500000 * TAX_RATE_10_PERCENT)
					+ (yearlySalary - 1000000) * TAX_RATE_20_PERCENT;
		}
	}

	private double calculateSalaryByDOJ(Double monthlySalary, LocalDate doj) {
		LocalDate today = LocalDate.now();
		LocalDate financialYearStart = LocalDate.of(doj.getYear(), Month.APRIL, 1);
		if (doj.isBefore(financialYearStart)) {
			financialYearStart = financialYearStart.minusYears(1);
		}

		LocalDate financialYearEnd = financialYearStart.plusYears(1).minusDays(1);

		long monthsWorked = ChronoUnit.MONTHS.between(doj.isBefore(financialYearStart) ? financialYearStart : doj,
				today.isAfter(financialYearEnd) ? financialYearEnd.plusDays(1) : today.plusDays(1));

		monthsWorked = Math.min(monthsWorked, 12);

		LocalDate lastDayOfDOJMonth = doj.withDayOfMonth(doj.lengthOfMonth());
		long daysWorkedInDOJMonth = ChronoUnit.DAYS.between(doj, lastDayOfDOJMonth) + 1;

		double dailySalary = monthlySalary / 30;

		double firstMonthSalary = dailySalary * daysWorkedInDOJMonth;

		double yearlySalary = (monthsWorked - 1) * monthlySalary + firstMonthSalary;

		return yearlySalary;
	}

	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		return employeeRepository.save(employee);
	}
}
