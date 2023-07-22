package com.das.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenExcel extends DataDrivenTest {

	private static int rows;

	private static int cols;

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		DataDrivenExcel.rows = rows;
	}

	public static int getCols() {
		return cols;
	}

	public static void setCols(int cols) {
		DataDrivenExcel.cols = cols;
	}

	public String[] fetchRangeDataFromSource() {
		String pathToExcel = System.getProperty("user.dir") + "\\ExcellDocs\\ContentData.xlsx";

		try {
			FileInputStream fis = new FileInputStream(pathToExcel);
			String result = "";
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("InputData");

			setRows(sheet.getLastRowNum());
			setCols(sheet.getRow(1).getLastCellNum());

			for (int i = 0; i <= rows; i++) {
				int count = 0;
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < getCols(); j++) {
					if (count == 0) {
						result = result + "|";
					}

					XSSFCell cell = row.getCell(j);
					if (cell == null || cell.getCellType() == CellType.BLANK) {
						result = result + "-";
					}
					// first convert all the excel data to string
					if (cell.getCellType().name().equalsIgnoreCase("String")) {
						result = result + cell.getStringCellValue();
					} else if (cell.getCellType().name().equalsIgnoreCase("Numeric")) {
						result = result + String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType().name().equalsIgnoreCase("Boolean")) {
						result = result + String.valueOf(cell.getBooleanCellValue());

					}
					result = result + "|";
					count++;
				}
				result = result + "\n";

			}

			System.out.println(result);
			result = result.substring(1, result.length() - 1);
			result = result.replaceAll("(\r\n|\n)", "");
			String temp[] = result.split("\\|");
			return temp;
		} catch (NullPointerException e) {
			System.out.println("You don't have any rows in the excel to work on");
		} catch (IOException e) {
			System.out.println("You have an exception- " + e);
		}
		return null;

	}

	public String fetchLastRangeValueFromSource() {
		String resultRangeValue = "";

		File filePath = new File(System.getProperty("user.dir") + "\\ExcellDocs\\LastRangeNumber.xlsx");
		if (filePath.exists()) {
			try {
				FileInputStream fisRangeValue = new FileInputStream(filePath);
				XSSFWorkbook workbookRangeValue = new XSSFWorkbook(fisRangeValue);
				XSSFSheet sheetRangeValue = workbookRangeValue.getSheet("Details");
				try {
					XSSFRow rowRangeValue = sheetRangeValue.getRow(sheetRangeValue.getLastRowNum());
					XSSFCell cellRangeValue = rowRangeValue.getCell(sheetRangeValue.getLastRowNum());

					if (cellRangeValue.getCellType().name().equalsIgnoreCase("String")) {
						resultRangeValue = resultRangeValue + cellRangeValue.getStringCellValue();
					} else if (cellRangeValue.getCellType().name().equalsIgnoreCase("Numeric")) {
						resultRangeValue = resultRangeValue + String.valueOf(cellRangeValue.getNumericCellValue());
					} else if (cellRangeValue.getCellType().name().equalsIgnoreCase("Boolean")) {
						resultRangeValue = resultRangeValue + String.valueOf(cellRangeValue.getBooleanCellValue());
					}
				}

				catch (NullPointerException e) {
					System.out.println("You don't have any rows in the excel to work on");
				}
			} catch (Exception e) {
				System.out.println("You have an exception- " + e);
			}
		}
		return resultRangeValue;
	}

	public static void excelWrittingRangeValue(XSSFWorkbook workbook, File filePath, XSSFSheet sheet)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);

		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(getEndValue());
		workbook.write(fos);
		System.out.println("Last Range Value added to the excel");
		fos.close();
	}

	@Override
	void setResultValuesBackToSource() {
		// TODO Auto-generated method stub

	}

}
