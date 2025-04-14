package com.flinki.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public void ExcelData() throws Exception
    {
        String filePath = ".\\src\\main\\Resources\\String.xlsx";
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

         public static void writeToCell(String filePath, String sheetName, int rowNum, int colNum, String value)
                 throws IOException {
             FileInputStream fis = new FileInputStream(filePath);
             Workbook wb = new XSSFWorkbook(fis);
             Sheet sheet = wb.getSheet(sheetName);
             Row row = sheet.getRow(rowNum);
             if (row == null)
                 row = sheet.createRow(rowNum);
             Cell cell = row.createCell(colNum);
             cell.setCellValue(value);
             FileOutputStream fos = new FileOutputStream(filePath);
             wb.write(fos);
             fos.close();
             wb.close();
         }
    
         
    public static String readToCell(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        String value = row.getCell(colNum).getStringCellValue();
        wb.close();
        return value;
    }

}