package com.flinki.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public void ExcelData() throws Exception
    {
     String filePath = ".\\src\\main\\java\\com\\flinki\\Resources\\String.xlsx";
     Workbook workbook;
     Sheet sheet;
     int rowNum;

    try (FileInputStream inputStream = new FileInputStream(filePath)) {
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet("Sheet1");
        rowNum = sheet.getLastRowNum() + 1;
    } catch (FileNotFoundException e) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Sheet1");
        rowNum = 0;
    }

    Row row = sheet.createRow(rowNum);
    Cell cell = row.createCell(0);
    cell.setCellValue(Data.randomEmail);

    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
        workbook.write(outputStream);
    }
}

}