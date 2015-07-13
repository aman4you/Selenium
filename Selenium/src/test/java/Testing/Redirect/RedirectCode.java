/**
 * Automate redirect code generator site.
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

public class RedirectCode {
    private WebDriver driver;
    private String BaseURL = "http://www.rapidtables.com/web/tools/redirect-generator.htm";
    private String DriverPath = "C:\\Users\\aman\\Downloads\\Programs\\Selenium\\IEDriverServer.exe";
    private String SheetName = "Sheet1";
    private String ExcelFilePath = "C:\\Users\\aman\\Downloads\\NewInfo\\RedirectCode.xlsx";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.ie.driver", DriverPath);
        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(BaseURL);
        Thread.sleep(2000);
    }

    @Test
    public void testFirst() throws Exception {
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        String Source = null;
        String Destination = null;
        String RedirectCode = null;

        for (int i = 1 ; i < 138 ; i++) {
            Row = ExcelWSheet.getRow(i);
            Source = Row.getCell(0).getRichStringCellValue().getString();
            Destination = Row.getCell(1).getRichStringCellValue().getString();
            RedirectCode =  getRedirect(Source, Destination);
            writeData(RedirectCode, i);
        }
    }

    private String getRedirect(String Source, String Destination) throws InterruptedException {
        // Enter URL of old page:
        driver.findElement(By.id("T1")).clear();
        driver.findElement(By.id("T1")).sendKeys(Source);

        // Enter URL of old page:
        driver.findElement(By.id("T2")).clear();
        driver.findElement(By.id("T2")).sendKeys(Destination);

        // Select Redirect Type
        new Select(driver.findElement(By.id("C1"))).selectByIndex(3);

        // Click Generate Code
        driver.findElement(By.name("B1")).click();

        // Get Redirect Code
        String RedirectCode = driver.findElement(By.id("TA1")).getText();
        String[] RedirectCodeText = RedirectCode.split("www.rapidtables.com");
        driver.findElement(By.id("TA1")).clear();
        return RedirectCodeText[1];
    }

    private void writeData(String RedirectCode, int i) throws IOException, InvalidFormatException {
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        XSSFCell Cell = null;
        Row = ExcelWSheet.getRow(i);

        Cell = Row.createCell(2);
        Cell.setCellType(Cell.CELL_TYPE_STRING);
        Cell.setCellValue(RedirectCode);

        FileOutputStream FileOutput = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(FileOutput);
        FileOutput.close();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
