package com.qa.aquabot.utility;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/Aqua-bot.xlsx";
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {

        Object[][] data;
        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
            Workbook book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }

        return data;
    }

}
