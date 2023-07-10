package com.thailife.pretest.thailifepretest.models;

import java.util.List;

public class Department {
	
	public Department() {}
	public Department(String employeeId, String employeeName, String mobileNo, String startDate, String departmentName,
			String departmentHeadName) {
		super();
		this.setEmployeeId(employeeId);
		this.employeeName = employeeName;
		this.mobileNo = mobileNo;
		this.startDate = startDate;
		this.departmentName = departmentName;
		this.departmentHeadName = departmentHeadName;
	}
	
	private String employeeId;
	private String employeeName;
	private String mobileNo;
	private String startDate;
	private String departmentName;
	private String departmentHeadName;
	private List<Employee> listEmployeeTeam;
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHeadName() {
		return departmentHeadName;
	}

	public void setDepartmentHeadName(String departmentHeadName) {
		this.departmentHeadName = departmentHeadName;
	}
	public List<Employee> getListEmployeeTeam() {
		return listEmployeeTeam;
	}
	public void setListEmployeeTeam(List<Employee> listEmployeeTeam) {
		this.listEmployeeTeam = listEmployeeTeam;
	}
	

}
