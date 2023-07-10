package com.thailife.pretest.thailifepretest.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thailife.pretest.thailifepretest.models.Department;
import com.thailife.pretest.thailifepretest.models.DepartmentStructure;
import com.thailife.pretest.thailifepretest.services.DepartmentService;
import com.thailife.pretest.thailifepretest.utils.ExcelGenerator;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
	
	private static final Logger logger = LogManager.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@CrossOrigin
	@GetMapping()
	public String healthCheck() {
		logger.info("Debugging log healthCheck");
		return "200 Ok.";
	}
	
	//No.1
	@CrossOrigin
	@GetMapping("/employee/dep/{departmentId}")
	public List<Department> getEmployeeByDepartmentId(@PathVariable String departmentId) {
		return departmentService.getEmployeeByDepartmentId(departmentId);
	}
	
	//No.1
	@CrossOrigin
	@GetMapping("/export-emp-to-excel/{departmentId}")
	public void exportEmpIntoExcelFile(@PathVariable String departmentId, HttpServletResponse response ) throws IOException {
		//logger.info("DepartmentId" + departmentId);
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
		List<Department> depEmpList = departmentService.getEmployeeByDepartmentId(departmentId);
		ExcelGenerator generator = new ExcelGenerator(depEmpList);
		generator.generateExcelFile(response);
		
	}
	
	//No.2
	@CrossOrigin
	@GetMapping("/employee/emp/{employeeId}")
	public Department getEmployeeByEmployeeId(@PathVariable String employeeId) {
		return departmentService.getEmployeeByEmployeeId(employeeId);
	}
	
	//No.3
	@CrossOrigin
	@GetMapping("/department-structure")
	public DepartmentStructure getDepartmentStruture() {
		return departmentService.getDepartmentStructure();
	}
}
