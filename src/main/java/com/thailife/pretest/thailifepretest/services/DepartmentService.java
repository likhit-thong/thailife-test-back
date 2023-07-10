package com.thailife.pretest.thailifepretest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.thailife.pretest.thailifepretest.models.Department;
import com.thailife.pretest.thailifepretest.models.DepartmentStructure;
import com.thailife.pretest.thailifepretest.models.Employee;

@Service
public class DepartmentService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Department> getEmployeeByDepartmentId(String departmentId) {
		String sql = String.format("SELECT dd.employee_id, ed.employee_name, ed.mobile_no, ed.start_date, d.department_name, c.employee_name AS department_head_name, ed.terminate_date\n"
				+ "FROM department_detail dd\n"
				+ "JOIN employee_detail ed ON dd.employee_id = ed.employee_id\n"
				+ "JOIN department d ON d.department_id = dd.department_id\n"
				+ "JOIN (\n"
				+ "    SELECT dh.department_id, ed.employee_name\n"
				+ "    FROM department_head dh\n"
				+ "    JOIN employee_detail ed ON ed.employee_id = dh.employee_id\n"
				+ "    WHERE dh.department_id = '006'\n"
				+ ") c ON c.department_id = d.department_id\n"
				+ "WHERE d.department_id = '%s'\n"
				+ "AND (ed.terminate_date IS NULL OR CAST(ed.terminate_date AS DATE) > CURRENT_DATE);", departmentId);
		return jdbcTemplate.query(sql, 
				(row, rowNum) -> 
					new Department(
						row.getString("employee_id"), 
						row.getString("employee_name"), 
						row.getString("mobile_no"), 
						row.getString("start_date"), 
						row.getString("department_name"), 
						row.getString("department_head_name"))
			);
	}
	
	
	
	public Department getEmployeeByEmployeeId(String employeeId) {
		
		String sql = String.format("SELECT\n"
				+ "  ed.employee_id,\n"
				+ "  ed.employee_name,\n"
				+ "  ed.mobile_no,\n"
				+ "  ed.start_date,\n"
				+ "  d.department_name,\n"
				+ "  edh.employee_name AS department_head_name,\n"
				+ "  dh.department_id\n"
				+ "FROM\n"
				+ "  employee_detail ed\n"
				+ "  JOIN department_detail dd ON ed.employee_id = dd.employee_id\n"
				+ "  JOIN department d ON dd.department_id = d.department_id\n"
				+ "  LEFT JOIN department_head dh ON d.department_id = dh.department_id\n"
				+ "  LEFT JOIN employee_detail edh ON dh.employee_id = edh.employee_id\n"
				+ "WHERE\n"
				+ "  ed.employee_id = '%s'\n"
				+ "  \n"
				+ "UNION\n"
				+ "  \n"
				+ "SELECT\n"
				+ "  ed.employee_id,\n"
				+ "  ed.employee_name,\n"
				+ "  ed.mobile_no,\n"
				+ "  NULL AS start_date,\n"
				+ "  NULL AS department_name,\n"
				+ "  NULL AS department_head_name,\n"
				+ "  NULL AS department_id\n"
				+ "FROM\n"
				+ "  employee_detail ed\n"
				+ "  JOIN department_detail dd ON ed.employee_id = dd.employee_id\n"
				+ "WHERE\n"
				+ "  dd.department_id IN (\n"
				+ "    SELECT department_id\n"
				+ "    FROM department_head\n"
				+ "    WHERE employee_id = '%s'\n"
				+ "  );", employeeId, employeeId);
		
		List<Department> listEmployee = jdbcTemplate.query(sql, 
				(row, rowNum) -> 
					new Department(
						row.getString("employee_id"), 
						row.getString("employee_name"), 
						row.getString("mobile_no"), 
						row.getString("start_date"), 
						row.getString("department_name"), 
						row.getString("department_head_name"))
		);
		
		Department dep = new Department();
		if(listEmployee.size() > 1) {
			dep = listEmployee.get(0);
			List<Department> tempListTeam = listEmployee.subList(1, listEmployee.size());
			List<Employee> listEmp = new ArrayList<Employee>();
			for (int index = 0; index < tempListTeam.size(); index++) {
			    Department tempDep = tempListTeam.get(index);
			    if(!tempDep.getEmployeeId().equals(dep.getEmployeeId())) {
			    	Employee emp = new Employee(tempDep.getEmployeeId(), tempDep.getEmployeeName(), tempDep.getMobileNo());
			    	listEmp.add(emp);
			    }
			}
			dep.setListEmployeeTeam(listEmp);
		} else if(listEmployee.size() == 1) {
			dep = listEmployee.get(0);
		}
		
		return dep;
	}
	
	public void departmentHierarchy(DepartmentStructure department) {
		List<DepartmentStructure> listAll = department.getListSubDepartment();
		if(listAll == null) {
			listAll = this.getDepartmentStructureByOrgCode(department.getDepartmentId());
			department.setListSubDepartment(listAll);
			if(listAll != null) {
				departmentHierarchy(department);
			}
		}else {
			for(DepartmentStructure dep: listAll) {
				departmentHierarchy(dep);
			}
		}
	}
	
	public DepartmentStructure getDepartmentStructure() {
		DepartmentStructure department = new DepartmentStructure();
		String sql = "SELECT d.department_id, d.department_name, e.employee_name AS head_department\n"
				+ "FROM department d\n"
				+ "JOIN department_head dh ON d.department_id = dh.department_id\n"
				+ "JOIN employee_detail e ON dh.employee_id = e.employee_id\n"
				+ "ORDER BY d.department_id\n"
				+ "LIMIT 1;";
		
		department = jdbcTemplate.queryForObject(sql, (row, rowNum) -> 
			new DepartmentStructure(
					row.getString("department_id"),
					row.getString("department_name"),
					row.getString("head_department")
			)
		);
		this.departmentHierarchy(department);
		return department;
	}
	
	public List<DepartmentStructure> getDepartmentStructureByOrgCode(String orgCode) {
		String mainSql = String.format("SELECT d.department_id, d.department_name, e.employee_name As head_employee_name\n"
				+ "FROM department d\n"
				+ "JOIN department_head dh ON d.department_id = dh.department_id\n"
				+ "JOIN employee_detail e ON dh.employee_id = e.employee_id\n"
				+ "WHERE d.org_code = '%s';", orgCode);
		
		return jdbcTemplate.query(mainSql, 
				(row, rowNum) -> 
					new DepartmentStructure(
						row.getString("department_id"), 
						row.getString("department_name"), 
						row.getString("head_employee_name")
					)
			);
	}
	
	
}
