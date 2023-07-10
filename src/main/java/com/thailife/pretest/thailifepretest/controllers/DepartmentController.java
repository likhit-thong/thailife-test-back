package com.thailife.pretest.thailifepretest.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thailife.pretest.thailifepretest.models.Department;
import com.thailife.pretest.thailifepretest.models.DepartmentStructure;
import com.thailife.pretest.thailifepretest.services.DepartmentService;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	
	private static final Logger logger = LogManager.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping()
	public String healthCheck() {
		logger.info("Debugging log healthCheck");
		return "200 Ok.";
	}
	
	//No.1
	@GetMapping("/employee/dep/{departmentId}")
	public List<Department> getEmployeeByDepartmentId(@PathVariable String departmentId) {
		return departmentService.getEmployeeByDepartmentId(departmentId);
	}
	
	//No.2
	@GetMapping("/employee/emp/{employeeId}")
	public Department getEmployeeByEmployeeId(@PathVariable String employeeId) {
		return departmentService.getEmployeeByEmployeeId(employeeId);
	}
	
	//No.3
	@GetMapping("/department-structure")
	public DepartmentStructure getDepartmentStruture() {
		return departmentService.getDepartmentStructure();
	}
}
