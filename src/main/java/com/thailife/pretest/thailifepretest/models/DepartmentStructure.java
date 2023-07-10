package com.thailife.pretest.thailifepretest.models;

import java.util.List;

public class DepartmentStructure {
	
	private String departmentId;
	private String departmentName;
	private String departmentHead;
	private List<DepartmentStructure> listSubDepartment;
	
	
	public DepartmentStructure() {
		// TODO Auto-generated constructor stub
	}

	
	public DepartmentStructure(String departmentId, String departmentName, String departmentHead) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
	}
	
	public DepartmentStructure(String departmentId, String departmentName, String departmentHead,
			List<DepartmentStructure> listSubDepartment) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentHead = departmentHead;
		this.listSubDepartment = listSubDepartment;
	}

	public String getDepartmentId() {
		return departmentId;
	}



	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public String getDepartmentHead() {
		return departmentHead;
	}



	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}
	

	public List<DepartmentStructure> getListSubDepartment() {
		return listSubDepartment;
	}



	public void setListSubDepartment(List<DepartmentStructure> listSubDepartment) {
		this.listSubDepartment = listSubDepartment;
	}
	
}
