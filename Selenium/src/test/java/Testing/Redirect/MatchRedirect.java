/**
 * Test redirect code for stage site.
 */

package Testing.Redirect;

/**
 * Created by aman on 7/1/2015.
 */
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MatchRedirect {
    private WebDriver driver;
    private String SheetName = "Sheet1";
    private String ExcelFilePath = "C:\\Users\\aman\\Downloads\\NewInfo\\RedirectCode.xlsx";
    private String DriverPath = "C:\\Users\\aman\\Downloads\\Programs\\Selenium\\chromedriver.exe";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", DriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testFirst() throws Exception {
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        String Destination = null;
        String RedirectCode = null;
        String AfterRedirect = null;
        String Result = null;

        for (int i = 1 ; i < 138 ; i++) {
            Row = ExcelWSheet.getRow(i);
            Destination = Row.getCell(1).getRichStringCellValue().getString();
            RedirectCode = Row.getCell(2).getRichStringCellValue().getString();
            AfterRedirect = getAfterRedirect(RedirectCode);
            Result = matchRedirect(Destination, AfterRedirect);
            writeData(AfterRedirect, Result, i);
        }
    }

    private String getAfterRedirect(String RedirectCode) throws InterruptedException {
        String[] SplitRedirectCode1 = RedirectCode.split("Redirect 301 ");
        String[] SplitRedirectCode2 = SplitRedirectCode1[1].split(" http://www.hcltech.com");
        String RedirectUrl = "http://hclstg.sites.innoraft.com" + SplitRedirectCode2[0];

        driver.get(RedirectUrl);
        String AfterRedirect = driver.getCurrentUrl();

        return AfterRedirect;
    }

    private String matchRedirect(String Destination, String AfterRedirect) {
        if (AfterRedirect.equals("http://www.hcltech.com/")) {
            AfterRedirect = "http://www.hcltech.com";
        }

        if (Destination.equals(AfterRedirect)) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    private void writeData(String AfterRedirect, String Result, int i) throws IOException, InvalidFormatException {
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        XSSFCell Cell3 = null;
        XSSFCell Cell4 = null;
        Row = ExcelWSheet.getRow(i);

        Cell3 = Row.createCell(3);
        Cell4 = Row.createCell(4);
        Cell3.setCellType(Cell3.CELL_TYPE_STRING);
        Cell4.setCellType(Cell4.CELL_TYPE_STRING);
        Cell3.setCellValue(AfterRedirect);
        Cell4.setCellValue(Result);

        FileOutputStream FileOutput = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(FileOutput);
        FileOutput.close();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
