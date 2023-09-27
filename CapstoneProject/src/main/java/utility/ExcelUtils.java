package utility;
import java.io.FileInputStream;

import java.util.Hashtable;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	
	

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {
	   try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			} catch (Exception e){
				throw (e);
			}
	}



public static Object[][]getData(String FilePath, String SheetName,String sTestCaseName)throws Exception
{   
	   FileInputStream ExcelFile = new FileInputStream(FilePath);
	   // Access the required test data sheet
	   ExcelWBook = new XSSFWorkbook(ExcelFile);
	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
		// reads data for only testCaseName
		int testStartRowNum = 1;
		while (!getCellData(SheetName,0,testStartRowNum).equals(sTestCaseName)) {
			testStartRowNum++;
		}
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;
		// calculate rows of data
				int rows = 0;
				while (!getCellData(SheetName, 0, dataStartRowNum + rows).equals("")) {
					rows++;
				}

				// calculate total cols
				int cols = 0;
				while (!getCellData(SheetName, cols, colStartRowNum).equals("")) {
					cols++;
				}
			Object[][] data = new Object[rows][1];
				// read the data
				int dataRow = 0;
				Hashtable<String, String> table = null;
				for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
					table = new Hashtable<String, String>();
					for (int cNum = 0; cNum < cols; cNum++) {
						String key =getCellData(SheetName, cNum, colStartRowNum);
						String value =getCellData(SheetName, cNum, rNum);
						table.put(key, value);
					}
					data[dataRow][0] = table;
					dataRow++;
				}
				
				return data;

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

/*public static String getCellData(String sheetName, int RowNum, int ColNum) throws Exception{

   try{

	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

	  String CellData = Cell.getStringCellValue();

	  return CellData;

	  }catch (Exception e){

		return"";

		}

	}*/






/**
 * Method to get cell value from a sheet based on column number and row number
 * 
 * @param sheetName Name of sheet in workbook. (type: String)
 * @param colNum    index of column number (type:int)
 * @param rowNum    index of row number (type:int)
 * @return cell data else throws an error (type: String)
 */
synchronized public static String getCellData(String sheetName, int colNum, int rowNum) {
	try {
		if (rowNum <= 0)
			return "";

		int index = ExcelWBook.getSheetIndex(sheetName);

		if (index == -1)
			return "";

		ExcelWSheet = ExcelWBook.getSheetAt(index);
		Row = ExcelWSheet.getRow(rowNum - 1);
		if (Row == null)
			return "";
		Cell = Row.getCell(colNum);
		if (Cell == null)
			return "";

		if (Cell.getCellType() == CellType.STRING)
			return Cell.getStringCellValue();
		else if (Cell.getCellType() == CellType.NUMERIC) {
			String cellText = String.valueOf(Cell.getNumericCellValue());
			return cellText;
		} else if (Cell.getCellType() == CellType.BLANK)
			return "";
		else
			return String.valueOf(Cell.getBooleanCellValue());
	} catch (Exception e) {
		e.getStackTrace();
		throw new Error("Got an exception while reading the data from row " + rowNum + " and column " + colNum);
	}
}

/**
 * Method to get Cell data from sheet based on column Name and row number
 * 
 * @param sheetName Name of sheet in workbook. (type: String)
 * @param colName   column name (type: String)
 * @param rowNum    index of row number (type: int)
 * @return cell data else throws an error (type:String)
 */
synchronized public String getCellData(String sheetName, String colName, int rowNum) {
	try {
		if (rowNum <= 0)
			return "";

		int index = ExcelWBook.getSheetIndex(sheetName);
		int col_Num = -1;
		if (index == -1)
			return "";

		ExcelWSheet = ExcelWBook.getSheetAt(index);
		Row = ExcelWSheet.getRow(0);
		for (int i = 0; i < Row.getLastCellNum(); i++) {
			if (Row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				col_Num = i;
				break;
			}
		}
		if (col_Num == -1)
			return "";

		ExcelWSheet = ExcelWBook.getSheetAt(index);
		Row = ExcelWSheet.getRow(rowNum - 1);
		if (Row == null)
			return "";
		Cell = Row.getCell(col_Num);

		if (Cell == null)
			return "";

		if (Cell.getCellType() == CellType.STRING)
			return Cell.getStringCellValue();
		else if (Cell.getCellType() == CellType.NUMERIC || Cell.getCellType() == CellType.FORMULA) {
			String cellText = String.valueOf(Cell.getNumericCellValue());
			return cellText;
		} else if (Cell.getCellType() == CellType.BLANK)
			return "";
		else
			return String.valueOf(Cell.getBooleanCellValue());
	} catch (Exception e) {
		e.getStackTrace();
		throw new Error(
				"Got an exception while reading the data from row " + rowNum + " and column name " + colName);
	}
}


}

