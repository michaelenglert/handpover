package org.appdynamics.handpover.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.appdynamics.handpover.config.Globals;

import java.io.*;

/**
 * Created by michi on 29.08.16.
 */
public class Excel {
    private FileInputStream fis;
    private XSSFWorkbook workbook;

    public void createFile (String excelFile) throws Exception{
        Folder.createFolder(Globals.EXCEL_FOLDER);
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream(Globals.EXCEL_FOLDER + excelFile);
        workbook.write(fos);
        fos.close();

    }
    public void writeToFile (String inSheet, int inRow, int inColumn, String data) {
        Sheet sheet = workbook.getSheet(inSheet);
        if (sheet == null) sheet = workbook.createSheet(inSheet);
        Row row = sheet.getRow(inRow);
        if (row == null) row = sheet.createRow(inRow);
        Cell cell = row.getCell(inColumn, Row.CREATE_NULL_AS_BLANK);
        cell.setCellValue(data);
    }
    public void openFile(String excelFile) throws Exception {
        fis = new FileInputStream(Globals.EXCEL_FOLDER + excelFile);
        workbook = new XSSFWorkbook(fis);
    }
    public void closeFile(String excelFile) throws Exception {
        fis.close();
        FileOutputStream fos = new FileOutputStream(Globals.EXCEL_FOLDER + excelFile);
        workbook.write(fos);
        fos.close();
    }
}
