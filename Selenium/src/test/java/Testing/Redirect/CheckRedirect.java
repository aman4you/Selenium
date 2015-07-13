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
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CheckRedirect {
    private WebDriver driver;
    private String SheetName = "Crawl Errors";
    private String ExcelFilePath = "C:\\Users\\aman\\Downloads\\CrawlError.xlsx";

    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testFirst() throws Exception {
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        String URL = null;
        String Recommend_Redirect = null;
        String Redirect = null;
        String Result = null;

        for (int i = 2 ; i < 643 ; i++) {
            Row = ExcelWSheet.getRow(i);
            URL = Row.getCell(0).getRichStringCellValue().getString();
            Recommend_Redirect = Row.getCell(2).getRichStringCellValue().getString();
            Redirect = getRedirect(URL);
            Result = matchRedirect(Redirect, Recommend_Redirect);
            writeData(Result, i);
        }
    }

    private String getRedirect(String URL) throws InterruptedException {
        driver.get(URL);
        String Redirect = driver.getCurrentUrl();

        return Redirect;
    }

    private String matchRedirect(String Redirect, String Recommend_Redirect) {
        if (Redirect.equals(Recommend_Redirect)) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    private void writeData(String Result, int i) throws IOException, InvalidFormatException {
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        XSSFCell Cell3 = null;
        Row = ExcelWSheet.getRow(i);

        Cell3 = Row.createCell(3);
        Cell3.setCellType(Cell3.CELL_TYPE_STRING);
        Cell3.setCellValue(Result);

        FileOutputStream FileOutput = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(FileOutput);
        FileOutput.close();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}