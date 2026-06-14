package com.Automation.baseline.utils;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
// import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.exception.CustomException;


/**
 * @Author: QA
 */
public class ExcelUtils {
	private static File file;
	private static FileInputStream inputStream;
	protected static Workbook workbook;

	protected static CellStyle style;
	protected static Font font;
	protected static Sheet sheet;
	protected static Row row;
	protected static Cell cell;
  	// private static XSSFSheet newsheet;
    // private static boolean createdSheet = false;

	protected static int totalRows = 0;
	protected static int totalCols = 0;

	/**
	 * Method for Opening InputStream before fetching data from Excel
	 */
	public static void openStream() throws IOException {
		try {

			file = new File(Constant.TestDataExcelFilePath);
			inputStream = new FileInputStream(file);
			String fileExtensionName = Constant.TestDataExcelFilePath.substring(Constant.TestDataExcelFilePath.indexOf("."));
			
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(file);
			} 
			else if (fileExtensionName.equals(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
			}
		}
		catch(Exception e) {
			throw new CustomException("File doesn't exist");
		}
	}

	/**
	 * Method for Closinig InputStream after fetching data from Excel
	 */
	public static void closeStream() throws IOException {
		try {
			inputStream.close();}
		catch(Exception e) {
			throw new CustomException("File doesn't exist");
		}
	}

	/**
	 * Method to read data from TestData.xlsx
	 * @param sheetName -  Sheet Name from which you want to read
	 * @param rowName - Row Name
	 * @param columnName - Column Name
	 * @return Cell Value in String Format
	 * @throws Exception
	 */
	public static String readExcel(String sheetName, String rowName, String columnName) throws Exception {
		Object result = new Object();
		try {
			sheet = workbook.getSheet(sheetName);
			totalRows = sheet.getLastRowNum();
			row = sheet.getRow(0);
			totalCols = row.getLastCellNum();

			for (int k = 1; k <= totalRows; k++) {
				
				Cell testCaseCell = sheet.getRow(k).getCell(0);
	            String testCaseID;

	            if (testCaseCell.getCellTypeEnum() == CellType.STRING) {
	                testCaseID = testCaseCell.getStringCellValue();
	            } else if (testCaseCell.getCellTypeEnum() == CellType.NUMERIC) {
	                testCaseID = String.valueOf((int) testCaseCell.getNumericCellValue());
	            } else {
	                throw new Exception("The cell type for testCaseID is invalid");
	            }
	            
				//String testCaseID = sheet.getRow(k).getCell(0).getStringCellValue();

				if (testCaseID.equalsIgnoreCase(rowName)) {
					for (int l = 1; l < totalCols; l++) {

						String testData_FieldName = sheet.getRow(0).getCell(l).getStringCellValue();

						if (testData_FieldName.equalsIgnoreCase(columnName)) {
							cell = sheet.getRow(k).getCell(l);//
							if (cell != null) {
								FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
								switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {


								case NUMERIC:
									if (DateUtil.isCellDateFormatted(cell)) {
										Date myDate = cell.getDateCellValue();
										SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
										result = formatter.format(myDate);
									} else {
										result = new BigDecimal(cell.getNumericCellValue()).toPlainString();
									}
									break;
								case STRING:
									result = cell.getStringCellValue();
									break;


								case BOOLEAN: // boolean value in excel
									result = cell.getBooleanCellValue();
									break;


								case BLANK:// blank value in excel
									result = cell.getStringCellValue();
									break;


								case ERROR: // Error value in excel
									result = cell.getErrorCellValue() + "";
									break;

								default:
									throw new Exception("The cell data type is invalid");
								}
                                 //logger.info(testData_FieldName + ": " + result);
								//System.out.println(testData_FieldName + ": " + result);
							}else{
								result= "";
								 //logger.info(testData_FieldName + ": " + result);
							}
						}
					}
					k = totalRows + 1;
				}
			}
		}
		catch (Exception ex) {
			throw ex;
		}
		return result.toString();
	}

	/**
	 * Method used to Set Row Number for Writing in Excel
	 * @param sheetName - Sheet Name in which you want to Write
	 */
	public static void setRowForWriteInExcel(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.createRow(sheet.getLastRowNum() + 1);
	}
	
	

    // /**
    //  * Method to write data to a specific column in new  Excel sheet
    //  *
    //  * @param sheetName   - Name of the sheet where data should be written
    //  * @param rowName     - Row identifier (e.g., primary key or unique identifier)
    //  * @param columnName  - Column name where data should be written
    //  * @param dataToWrite - Data to write into the specified cell
    //  * @throws Exception
    //  */
	// public static void addColumnAndWriteData(String sheetName, String newColumnName, String[] dataToWrite) throws Exception {
    //     try {
    //         sheet = workbook.getSheet(sheetName);
    //         int totalRows = sheet.getLastRowNum();
    //         System.out.println(totalRows);
    //         Row headerRow = sheet.getRow(0);
    //         totalCols = headerRow.getLastCellNum();

    //         // Check if the new column already exists
    //         int newColumnIndex = -1;
    //         for (int i = 0; i < totalCols; i++) {
    //             if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(newColumnName)) {
    //             	System.out.println("column already cradted");
    //                 newColumnIndex = i;
    //                 break;
    //             }
    //         }

    //         // If column does not exist, create a new column
    //         if (newColumnIndex == -1) {
    //             newColumnIndex = totalCols;
    //             System.out.println(totalCols);
    //             Cell newHeaderCell = headerRow.createCell(newColumnIndex);
    //             System.out.println(newHeaderCell);
    //             newHeaderCell.setCellValue(newColumnName);
    //             System.out.println(newHeaderCell);
    //         }

    //         // Write data to the new column for each row
    //         for (int k = 1; k <= totalRows; k++) {
    //             Row row = sheet.getRow(k);
    //             if (row == null) {
    //             	System.out.println("row is null");
    //                 row = sheet.createRow(k);
    //             }
    //             Cell newCell = row.createCell(newColumnIndex);
    //             newCell.setCellValue(dataToWrite[k - 1]);
    //         }

    //         // Write the updated workbook to the file
    //         try (FileOutputStream fileOutputStream = new FileOutputStream(Constant.TestDataExcelFilePath.substring(Constant.TestDataExcelFilePath.indexOf(".")))) {
    //             workbook.write(fileOutputStream);
    //         }

    //         System.out.println("Excel file updated successfully!");

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } 
    //     finally {
    //         try {
    //             if (workbook != null) {
    //                 workbook.close();
    //             }
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }


  	
//	public static void writeExcel(int total, int i, String cellvalue1, String cellvalue2, String cellvalue3) throws Exception {
//	    try {
//	        if (!createdSheet) {
//	            // Create a new sheet with a dynamic name based on the current date
//	            String dynamicSheetName = new SimpleDateFormat("yyyyMMdd").format(new Date());
//	            newsheet = (XSSFSheet) workbook.createSheet("CR_US_OrderRegression" + dynamicSheetName);
//
//	            // Create the header row in the new sheet if it doesn't exist
//	            XSSFRow headerRow = newsheet.createRow(0);
//	            headerRow.createCell(0).setCellValue("ScenarioName");
//	            headerRow.createCell(1).setCellValue("PaymentMethod");
//	            headerRow.createCell(2).setCellValue("Placed order Number");
//
//	            createdSheet = true;
//	        }
//
//	        // Create or update data rows
//	        XSSFRow row = newsheet.createRow(i);
//	        row.createCell(0).setCellValue(cellvalue1);
//	        row.createCell(1).setCellValue(cellvalue2);
//	        row.createCell(2).setCellValue(cellvalue3);
//
//	        // Write the updated workbook to the file
//	        try (FileOutputStream fileOutputStream = new FileOutputStream(Constant.TestDataExcelFilePath)) {
//	            workbook.write(fileOutputStream);
//	        }
//
//	        // Close the workbook if all operations are completed
//	        if (total == i) {
//	            workbook.close();
//	        }
//
//	        System.out.println("Excel file updated successfully!");
//
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            if (workbook != null) {
//	                workbook.close(); // Ensure workbook is closed in case of any exceptions
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
	
	/**
	 * Method used to Write Data in the next empty Row for a Given Column
	 * @param columnName - Column Name
	 * @param value - Value you want to Write in the Cell
	 * @throws Exception
	 */
	public static void writeExcel(String columnName, Object value) throws Exception {
		try {
			totalCols = sheet.getRow(0).getLastCellNum();
			
			for (int l = 1; l < totalCols; l++) {

				String testData_FieldName = sheet.getRow(0).getCell(l).getStringCellValue();

				if (testData_FieldName.equalsIgnoreCase(columnName)) {
					
					String[] temp = value.getClass().toString().split("\\.");
					String type = temp[temp.length - 1];
					
					switch (type) {

						case "String":
										row.createCell(l).setCellValue((String)value);
										break;
										
						case "Calendar":
										row.createCell(l).setCellValue((Calendar)value);	
										break;
							
						case "Boolean":
										row.createCell(l).setCellValue((boolean)value);
										break;

						case "Short":
						case "Integer":
						case "Long":
						case "Float":
						case "Double":
										row.createCell(l).setCellValue(String.valueOf(value));	
										break;

						case "Character":
										row.createCell(l).setCellValue((char)value);
										break;


						default:
										throw new Exception("The cell data type is invalid");
						}
				}
			}
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Method used to write data in Excel File using Stream
	 */
	public static void writeInExcelFileUsingStream() throws Exception {
		
		try {
			row.createCell(0).setCellValue(sheet.getLastRowNum());
			closeStream();
			String outputFilePath = System.getProperty("user.dir") + "/" + Constant.TestDataExcelFilePath;
			try (FileOutputStream fileOut = new FileOutputStream(outputFilePath + ".new")) {
			    workbook.write(fileOut);
			    workbook.close();
				fileOut.close();
			}
			Files.delete(Paths.get(outputFilePath));
			Files.move(Paths.get(outputFilePath + ".new"), Paths.get(outputFilePath));
			
			System.out.println("Value written successfully in Excel");
		
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * Method to get Row Count for a Given Sheet in Excel
	 * @param sheetName - Name of the Sheet
	 * @return - Number of Row Entry in given Sheet
	 */
	public static int getRowCount(String sheetName) {
		return workbook.getSheet(sheetName).getLastRowNum();
	}
}
