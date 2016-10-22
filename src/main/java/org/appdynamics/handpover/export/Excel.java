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

    public void createInitial() throws Exception {
        InputStream template_is;
        template_is = getClass().getResourceAsStream(Globals.ROOT + Globals.EXCEL_FILE);
        XSSFWorkbook workbook = new XSSFWorkbook(template_is);
        template_is.close();
        File outputFolder = new File (Globals.OUTPUT_FOLDER);

        if (!outputFolder.exists()) {
            Boolean result = outputFolder.mkdir();
            if (!result) {
                throw new RuntimeException(Globals.ERROR_FOLDER);
            }
        }
        FileOutputStream fos = new FileOutputStream(Globals.OUTPUT_FOLDER+Globals.EXCEL_FILE);
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
    public void openFile() throws Exception {
        fis = new FileInputStream(Globals.OUTPUT_FOLDER+Globals.EXCEL_FILE);
        workbook = new XSSFWorkbook(fis);
    }
    public void closeFile() throws Exception {
        fis.close();
        FileOutputStream fos = new FileOutputStream(Globals.OUTPUT_FOLDER+Globals.EXCEL_FILE);
        workbook.write(fos);
        fos.close();
    }
}
