package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataTestUtility {

    @DataProvider(name = "excelData")
    public Object[][] provideData(Method method) throws IOException {
        //String filePath = "C:\\Users\\u1234713\\Documents\\GitHub\\Team2_OrangeHRM\\CapstoneProject\\Resources\\DataDrivenTesting\\DataDriven.xlsx";
        Path filePath = Paths.get("Resources/DataDrivenTesting/DataDriven.xlsx");
        FileInputStream fis = new FileInputStream(filePath.toString());
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Valid");
        List<Object[]> data = new ArrayList<>();

        for (Row row : sheet) {
            Cell methodCell = row.getCell(0);
            if (methodCell.getStringCellValue().equals(method.getName())) {
                List<Object> rowData = new ArrayList<>();
                for (int i = 1; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowData.add(cell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }
                }
                data.add(rowData.toArray());
                break; // As each method's data is only once, break after finding it
            }
        }
        workbook.close();
        fis.close();
        return data.toArray(new Object[0][]);
    }
}

