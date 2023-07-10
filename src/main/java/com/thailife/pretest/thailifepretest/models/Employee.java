package com.thailife.pretest.thailifepretest.models;

public class Employee {

	public Employee(String employeeId, String employeeName, String mobileNo) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.mobileNo = mobileNo;
	}
	private String employeeId;
	private String employeeName;
	private String mobileNo;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
