package com.thailife.pretest.thailifepretest.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.thailife.pretest.thailifepretest.models.EmpMaster;

@Service
public class EmployeeService {
	
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public EmpMaster getEmpMasterData() {
		
		EmpMaster empMaster = new EmpMaster();
		Thread departmentThread = new Thread(() -> {
		    String departmentSql = "SELECT DISTINCT emp_department_id, emp_department_name\n"
		            + "FROM emp_department\n"
		            + "ORDER BY emp_department_name ASC;";
		    List<Map<String, Object>> departmentResult = jdbcTemplate.queryForList(departmentSql, new Object[]{});
		    empMaster.setListDepartment(departmentResult);
		});

		Thread positionThread = new Thread(() -> {
		    String positionSql = "SELECT emp_position_id, emp_position_name, emp_department_id\n"
		            + "FROM emp_position\n"
		            + "ORDER BY emp_position_name ASC;";
		    List<Map<String, Object>> positionResult = jdbcTemplate.queryForList(positionSql, new Object[]{});
		    empMaster.setListPosition(positionResult);
		});

		departmentThread.start();
		positionThread.start();

		try {
		    departmentThread.join();
		    positionThread.join();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		return empMaster;
	}
	
	
	public List<Map<String, Object>> searchEmployee( String qr, String opt ){
		
		try {
			
			String sql = "SELECT\n" +
			        " emp_employee.*,\n" +
			        " emp_position.emp_position_name,\n" +
			        " emp_department.emp_department_id,\n" +
			        " emp_department.emp_department_name\n" +
			        "FROM\n" +
			        " emp_employee\n" +
			        "JOIN\n" +
			        " emp_position ON emp_employee.emp_position_id = emp_position.emp_position_id\n" +
			        "JOIN\n" +
			        " emp_department ON emp_position.emp_department_id = emp_department.emp_department_id\n" +
			        "WHERE\n";
			if(opt.equals("empId")) {
			   sql += " emp_employee.emp_id LIKE '%" + qr + "%'\n;";
			} else if(opt.equals("thaiId")) {
				sql += " emp_employee.emp_thaiid LIKE '%" + qr + "%'\n;";
			}else if(opt.equals("name")) {
				sql += " emp_employee.emp_fullname LIKE '%" + qr + "%'\n;";
			}
			
			List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, new Object[] {});
			return result;
			
		}catch(Exception e) {
			logger.error(e);
			return new ArrayList<>();
		}
		
	}
	
	
//	private Date convertStrDateToDate(String strDate) {
//		// "10/07/2023";
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date empDate = null;
//		
//		if(strDate.equals("")) {
//			return empDate;
//		}
//
//		try {
//			empDate = dateFormat.parse(strDate);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}
//		return empDate;
//	}
//	
//	private String convertStrFormatDate(Date date) {
//		// Format empBirthDate as "DD/MM/YYYY"
//	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	    return dateFormat.format(date);
//	}
		
	public int saveEmp(EmpMaster empMaster) {
		String sql = "INSERT INTO emp_employee (emp_id, emp_prefix_id, emp_gender, emp_fullname, emp_thaiid, emp_birthdate, emp_current_address, emp_domicile_address, emp_mobile, emp_email, emp_position_id, emp_type, emp_status, emp_action_status, emp_startdate, emp_enddate, emp_create_by, emp_updated_by) " +
	             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String empPrefixId = "EMP";
		String empId = empPrefixId + empMaster.getEmpDivision() + empMaster.getEmpPosition() + this.generateEmpIdFromMax();
		//logger.info("Next EmpId ---> " + empId);
		
		int rowsAffected = jdbcTemplate.update(sql, 
				empId, 
				empPrefixId, 
				empMaster.getEmpGender(), 
				empMaster.getEmpFullName(),
				empMaster.getEmpThaiId(), 
				empMaster.getEmpBirthDate(), 
				empMaster.getEmpCurrentAddress(), 
				empMaster.getEmpDomicileAddress(), 
				empMaster.getEmpMobile(), 
				empMaster.getEmpEmail(), 
				empMaster.getEmpPosition(), 
				empMaster.getEmpType(), 
				empMaster.getEmpStatus(), 
				empMaster.getEmpActionStatus(), 
				empMaster.getEmpStartDate(), 
				empMaster.getEmpEndDate(), 
				empMaster.getEmpCreateBy(), 
				empMaster.getEmpUpdatedBy()
			);
		return rowsAffected;

	}
	
	public int updateEmp(EmpMaster empMaster, String empId) {
	    String sql = "UPDATE emp_employee SET emp_gender = ?, emp_fullname = ?, emp_thaiid = ?, emp_birthdate = ?, emp_current_address = ?, emp_domicile_address = ?, emp_mobile = ?, emp_email = ?, emp_position_id = ?, emp_type = ?, emp_status = ?, emp_status_desc = ?, emp_action_status = ?, emp_startdate = ?, emp_enddate = ?, emp_updated_by = ? WHERE emp_id = ?";
	    //logger.info("EMP ---> ", empId);
	    int rowsAffected = jdbcTemplate.update(sql,
	        empMaster.getEmpGender(),
	        empMaster.getEmpFullName(),
	        empMaster.getEmpThaiId(),
	        empMaster.getEmpBirthDate(),
	        empMaster.getEmpCurrentAddress(),
	        empMaster.getEmpDomicileAddress(),
	        empMaster.getEmpMobile(),
	        empMaster.getEmpEmail(),
	        empMaster.getEmpPosition(),
	        empMaster.getEmpType(),
	        empMaster.getEmpStatus(),
	        empMaster.getEmpStatusDesc(),
	        empMaster.getEmpActionStatus(),
	        empMaster.getEmpStartDate(),
	        empMaster.getEmpEndDate(),
	        empMaster.getEmpUpdatedBy(),
	        empId
	    );
	    
	    return rowsAffected;
	}

	
	public String generateEmpIdFromMax() {
		String sql = "SELECT MAX(CAST(SUBSTRING(emp_id, LENGTH(emp_id) - 2) AS INTEGER)) FROM emp_employee";
		Integer maxLastThreeDigits = jdbcTemplate.queryForObject(sql, Integer.class);
		if (maxLastThreeDigits != null) {
		    // Format the maximum value to three digits
		    String formattedMaxValue = String.format("%03d", ++maxLastThreeDigits);
		    return formattedMaxValue;
		}
		return "001";
	}
	
	
}
