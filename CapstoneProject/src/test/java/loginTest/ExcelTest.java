package loginTest;

import org.testng.annotations.Test;

import utility.ExcelDataTestUtility;

public class ExcelTest {

    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataTestUtility.class)
    public void Method1(String username, String info) {
        System.out.println("Username: " + username);
        System.out.println("Info: " + info);
    }

    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataTestUtility.class)
    public void Method2(String username, String info, String email) {
        System.out.println("Username: " + username);
        System.out.println("Info: " + info);
        System.out.println("Email: " + email);
    }
}

