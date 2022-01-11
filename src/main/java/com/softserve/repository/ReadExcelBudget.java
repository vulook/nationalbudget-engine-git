package com.softserve.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;

import com.softserve.entity.NationalBudget;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class ReadExcelBudget {

    protected static final String OUTPUT_PATH_FILE = "src\\main\\resources\\DatabaseBudget.xls";

    protected ReadExcelBudget() {
    };

    protected static List<NationalBudget> budgetList() {
        List<NationalBudget> budget = new ArrayList<>();
        Workbook wb;
        Sheet sheet;
        Row row;

        try (FileInputStream excelFileToRead = new FileInputStream(OUTPUT_PATH_FILE)) {

            wb = readExcelFileUsingApachePOI(excelFileToRead);
            if (wb != null) {
                sheet = wb.getSheetAt(numberSheetDod3(wb));
                System.out.println("Excel Reading - Number Of Sheets: " + wb.getNumberOfSheets() + ", Active Sheet: "
                        + sheet.getSheetName());

                removeMergedRegion(sheet);

                clearRowCell(sheet, 6);

                int oneColumn = checkColumnEmptyCell(sheet, 40, 1);

                for (int index = 0; index < sheet.getPhysicalNumberOfRows(); index++) {
                    if (index > 0) {
                        row = sheet.getRow(index);
                        NationalBudget stateBudget = new NationalBudget();
                        if (!Objects.equals(getCellValue(wb, sheet, row, 2 + oneColumn), "")
                                && (convertStringToDouble(getCellValue(wb, sheet, row, 13 + oneColumn))) >= 0) {
                            stateBudget.setCodeB(convertStringToInt(getCellValue(wb, sheet, row, oneColumn)));
                            stateBudget.setCodeF(convertStringToInt(getCellValue(wb, sheet, row, 1 + oneColumn)));
                            stateBudget.setNameExpend(getCellValue(wb, sheet, row, 2 + oneColumn));
                            stateBudget.setExpendG(convertStringToDouble(getCellValue(wb, sheet, row, 3 + oneColumn)));
                            stateBudget.setConsumG(convertStringToDouble(getCellValue(wb, sheet, row, 4 + oneColumn)));
                            stateBudget.setDevelG(convertStringToDouble(getCellValue(wb, sheet, row, 7 + oneColumn)));
                            stateBudget.setExpendS(convertStringToDouble(getCellValue(wb, sheet, row, 8 + oneColumn)));
                            stateBudget.setConsumS(convertStringToDouble(getCellValue(wb, sheet, row, 9 + oneColumn)));
                            stateBudget.setDevelS(convertStringToDouble(getCellValue(wb, sheet, row, 12 + oneColumn)));
                            stateBudget.setExpenditures(convertStringToDouble(getCellValue(wb, sheet, row, 13 + oneColumn)));
                            budget.add(stateBudget);
                        }
                    }
                }
            } else {
                System.out.println("Workbook not found");
            }
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
        System.out.println("Done!");
        return budget;
    }

    protected static Workbook readExcelFileUsingApachePOI(FileInputStream excelFileToRead) {
        Workbook wb = null;
        if (excelFileToRead != null) {
            try (InputStream is = FileMagic.prepareToCheckMagic(excelFileToRead)) {
                FileMagic fm = FileMagic.valueOf(is);
                if (fm == FileMagic.OOXML) {
                    OPCPackage pkg = OPCPackage.open(is);
                    wb = XSSFWorkbookFactory.createWorkbook(pkg);
                } else {
                    POIFSFileSystem fs = new POIFSFileSystem(is);
                    wb = WorkbookFactory.create(fs);
                }
            } catch (FileNotFoundException | InvalidFormatException ife) {
                System.err.format("FileNotFoundException: %s%n", ife);
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }
        return wb;
    }

    protected static int numberSheetDod3(Workbook wb) {
        int mySheet = 0;
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            System.out.println("Sheet => " + i + " " + "name: " + sheet.getSheetName());
            System.out.println("Sheet => " + i + " " + "firstRowNum: " + sheet.getFirstRowNum());
            System.out.println("Sheet => " + i + " " + "lastRowNum: " + sheet.getLastRowNum());

            if ((sheet.getSheetName().equals("дод 3")) || (sheet.getSheetName().equals("дод.3"))
                    || (sheet.getSheetName().equals("д3")) || (sheet.getSheetName().equals("dod_3"))
                    || (sheet.getSheetName().equals("dod3")) || (sheet.getSheetName().equals("d3"))
                    || (sheet.getSheetName().equals("dod 3"))) {
                System.err.println("Found sheet: DOD 3");
                mySheet = i;
            }
        }
        return mySheet;
    }

    protected static void removeMergedRegion(Sheet sheet) {
        while (sheet.getNumMergedRegions() > 0) {
            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                sheet.removeMergedRegion(i);
            }
        }

        sheet.createFreezePane(0, 0);

        for (int i = 0; i < 10; i++) {
            sheet.setColumnHidden(i, false);
            sheet.setDefaultColumnWidth(8);
        }
        System.out.println("Remove Merged Region");
    }

    protected static int checkColumnEmptyCell(Sheet sheet, int r, int c) {
        for (int i = 30; i < r; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < c; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    protected static boolean isDouble(Cell cell) {
        try {
            Double.parseDouble(cell.toString());
            System.out.println("true");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("false");
            return false;
        }
    }

    protected static void clearRowCell(Sheet sheet, int r) {
        String cellCoordinate = null;
        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i < r; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            Iterator<Cell> cellIt = row.cellIterator();
            while (cellIt.hasNext()) {
                Cell cell = cellIt.next();
                String empno = formatter.formatCellValue(cell);

                if (empno.contains("Всього:") || empno.contains("ВСЬОГО:") || isDouble(cell)) {
                    break;
                } else {
                    cell.setCellValue("");
                    cellCoordinate = "(" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")";
                    System.out.println("Clear cell => " + cellCoordinate);
                }
            }
        }
    }

    protected static int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        } else {
            try {
                result = Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                System.err.format("ParseException: %s%n", ex);
            }
        }
        return result;
    }

    protected static double convertStringToDouble(String str) {
        double doubleNumb = 0.0;
        double result = 0.0;
        NumberFormat nf = NumberFormat.getInstance();

        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        } else {
            try {
                doubleNumb = (nf.parse(str).doubleValue() / 1000);
            } catch (ParseException ex) {
                System.err.format("ParseException: %s%n", ex);
            }
            BigDecimal bigDecimal = BigDecimal.valueOf(doubleNumb);
            bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);
            result = bigDecimal.doubleValue();
        }
        return result;
    }

    protected static String getCellValue(Workbook wb, Sheet sheet, Row row, int numColumn) {
        String cellValue;
        String cellCoordinate;
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();
        if (row == null) {
            row = sheet.createRow(numColumn);
        }

        Cell cell = row.getCell(numColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cellCoordinate = "(" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")";

//        if (cell.getCellType() == CellType.FORMULA) {
//            System.out.println("Formula is: " + cell.getCellFormula());
//            System.out.println("cell.getCachedFormulaResultType(): " + cell.getCachedFormulaResultType());
//            evaluator.evaluateFormulaCell(cell);
//        }
        try {

            cellValue = formatter.formatCellValue(cell, evaluator);

        } catch (NumberFormatException | EmptyStackException | FormulaParseException fpe) {
            System.err.format("Getting error code for %s failed!: %s%n", cellCoordinate, fpe.getMessage());
            return ErrorEval.getText(cell.getErrorCellValue());
        }
        return cellValue;
    }

}
