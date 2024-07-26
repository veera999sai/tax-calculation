package com.task.taxcalculation.model;

import lombok.Builder;

@Builder
public class EmployeeTaxResponse {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private double yearlySalary;
	private double taxAmount;
	private double cessAmount;
	private double totalTaxDeduction;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(double cessAmount) {
		this.cessAmount = cessAmount;
	}

	public double getTotalTaxDeduction() {
		return totalTaxDeduction;
	}

	public void setTotalTaxDeduction(double totalTaxDeduction) {
		this.totalTaxDeduction = totalTaxDeduction;
	}
}
