package com.thailife.pretest.thailifepretest.models;

import java.util.List;
import java.util.Map;

public class EmpMaster {

	private String empId;
	private String empType;
	private String empGender;
	private String empFullName;
	private String empThaiId;
	private String empBirthDate;
	private String empCurrentAddress;
	private String empDomicileAddress;
	private String empMobile;
	private String empEmail;
	private String empDivision;
	private String empPosition;
	private String empStatus;
	private String empStatusDesc;
	private String empActionStatus;
	private String empStartDate;
	private String empEndDate;
	private String empCreateBy;
	private String empCreateDate;
	private String empUpdatedBy;
	private String empUpdatedDate;
	
	
	private List<Map<String, Object>> listDepartment;
	private List<Map<String, Object>> listPosition;
	
	public EmpMaster() {
		//TODO
	}
	
	public EmpMaster(String empId, String empType, String empGender, String empFullName, String empThaiId,
			String empBirthDate, String empCurrentAddress, String empDomicileAddress, String empMobile, String empEmail,
			String empDivision, String empPosition, String empStatus, String empStatusDesc, String empActionStatus,
			String empStartDate, String empEndDate, String empCreateBy, String empCreateDate, String empUpdatedBy,
			String empUpdatedDate, List<Map<String, Object>> listDepartment, List<Map<String, Object>> listPosition) {
		super();
		this.empId = empId;
		this.empType = empType;
		this.empGender = empGender;
		this.empFullName = empFullName;
		this.empThaiId = empThaiId;
		this.empBirthDate = empBirthDate;
		this.empCurrentAddress = empCurrentAddress;
		this.empDomicileAddress = empDomicileAddress;
		this.empMobile = empMobile;
		this.empEmail = empEmail;
		this.empDivision = empDivision;
		this.empPosition = empPosition;
		this.empStatus = empStatus;
		this.empStatusDesc = empStatusDesc;
		this.empActionStatus = empActionStatus;
		this.empStartDate = empStartDate;
		this.empEndDate = empEndDate;
		this.empCreateBy = empCreateBy;
		this.empCreateDate = empCreateDate;
		this.empUpdatedBy = empUpdatedBy;
		this.empUpdatedDate = empUpdatedDate;
		this.listDepartment = listDepartment;
		this.listPosition = listPosition;
	}
		
	public List<Map<String, Object>> getListDepartment() {
		return listDepartment;
	}
	public void setListDepartment(List<Map<String, Object>> listDepartment) {
		this.listDepartment = listDepartment;
	}
	public List<Map<String, Object>> getListPosition() {
		return listPosition;
	}
	public void setListPosition(List<Map<String, Object>> listPosition) {
		this.listPosition = listPosition;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getEmpFullName() {
		return empFullName;
	}
	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}
	public String getEmpThaiId() {
		return empThaiId;
	}
	public void setEmpThaiId(String empThaiId) {
		this.empThaiId = empThaiId;
	}
	public String getEmpBirthDate() {
		return empBirthDate;
	}
	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}
	public String getEmpCurrentAddress() {
		return empCurrentAddress;
	}
	public void setEmpCurrentAddress(String empCurrentAddress) {
		this.empCurrentAddress = empCurrentAddress;
	}
	public String getEmpDomicileAddress() {
		return empDomicileAddress;
	}
	public void setEmpDomicileAddress(String empDomicileAddress) {
		this.empDomicileAddress = empDomicileAddress;
	}
	public String getEmpMobile() {
		return empMobile;
	}
	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpDivision() {
		return empDivision;
	}
	public void setEmpDivision(String empDivision) {
		this.empDivision = empDivision;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	public String getEmpStartDate() {
		return empStartDate;
	}
	public void setEmpStartDate(String empStartDate) {
		this.empStartDate = empStartDate;
	}
	public String getEmpEndDate() {
		return empEndDate;
	}
	public void setEmpEndDate(String empEndDate) {
		this.empEndDate = empEndDate;
	}
	public String getEmpCreateBy() {
		return empCreateBy;
	}
	public void setEmpCreateBy(String empCreateBy) {
		this.empCreateBy = empCreateBy;
	}
	public String getEmpCreateDate() {
		return empCreateDate;
	}
	public void setEmpCreateDate(String empCreateDate) {
		this.empCreateDate = empCreateDate;
	}
	public String getEmpUpdatedBy() {
		return empUpdatedBy;
	}
	public void setEmpUpdatedBy(String empUpdatedBy) {
		this.empUpdatedBy = empUpdatedBy;
	}
	public String getEmpUpdatedDate() {
		return empUpdatedDate;
	}
	public void setEmpUpdatedDate(String empUpdatedDate) {
		this.empUpdatedDate = empUpdatedDate;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getEmpActionStatus() {
		return empActionStatus;
	}

	public void setEmpActionStatus(String empActionStatus) {
		this.empActionStatus = empActionStatus;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpStatusDesc() {
		return empStatusDesc;
	}

	public void setEmpStatusDesc(String empStatusDesc) {
		this.empStatusDesc = empStatusDesc;
	}
}
