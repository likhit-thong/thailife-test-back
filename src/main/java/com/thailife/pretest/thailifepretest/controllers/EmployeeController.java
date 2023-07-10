package com.thailife.pretest.thailifepretest.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thailife.pretest.thailifepretest.models.EmpMaster;
import com.thailife.pretest.thailifepretest.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	//private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@CrossOrigin
	@GetMapping("/emp/search")
	@ResponseBody
	public List<Map<String, Object>> getEmployeeByQuery(@RequestParam("qr") String qr, @RequestParam("opt") String opt) {
		return employeeService.searchEmployee(qr, opt);
	}
	
	@CrossOrigin
	@GetMapping("/emp/master")
	public EmpMaster getEmpMasterData() {
		return employeeService.getEmpMasterData();
	}
	
	@CrossOrigin
	@PostMapping("/emp")
	@ResponseBody
	public int saveEmp(@RequestBody EmpMaster empMaster) {
		return employeeService.saveEmp(empMaster);
	}
	
	@CrossOrigin
	@PostMapping("/emp/{empId}")
	@ResponseBody
	public int updateEmp(@PathVariable String empId, @RequestBody EmpMaster empMaster) {
		return employeeService.updateEmp(empMaster, empId);
	}
	
	@CrossOrigin
	@GetMapping("/emp/next")
	@ResponseBody
	public String getNextEmpId() {
		return employeeService.generateEmpIdFromMax();
	}
}
