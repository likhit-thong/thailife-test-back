package com.thailife.pretest.thailifepretest.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thailife.pretest.thailifepretest.models.Department;

public class ExcelGenerator {
	
	private List<Department> depEmpList;
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    
    public ExcelGenerator(List<Department> depEmpList) {
    	this.depEmpList = depEmpList;
    	workbook = new XSSFWorkbook();
    }
    
    private void writeHeader() {
    	
        sheet = workbook.createSheet("Employee");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
       
        createCell(row, 0, "EmployeeId", style);
        createCell(row, 1, "EmployeeName", style);
        createCell(row, 2, "mobileNo", style);
        createCell(row, 3, "StartDate", style);
        createCell(row, 4, "DepartmentName", style);
        createCell(row, 5, "DepartmentHeadName", style);
        
    }
    
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
        	if(valueOfCell == null) {
        		cell.setCellValue("");
        	}else {
        		cell.setCellValue((Boolean) valueOfCell);
        	}
            
        }
        cell.setCellStyle(style);
    }
    
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Department record: depEmpList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getEmployeeId(), style);
            createCell(row, columnCount++, record.getEmployeeName(), style);
            createCell(row, columnCount++, record.getMobileNo(), style);
            createCell(row, columnCount++, record.getStartDate(), style);
            createCell(row, columnCount++, record.getDepartmentName(), style);
            createCell(row, columnCount++, record.getDepartmentHeadName(), style);
        }
    }
    
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
