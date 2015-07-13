package Testing.HCL;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

import static junit.framework.Assert.assertTrue;

/**
 * Created by om on 11/12/2014.
 */
public class HCLFunctions {
    private WebDriver driver;
    private WebDriverWait wait;
    private String SheetName = "ContentType";
    private String ScreensPath = "C:\\Users\\om\\Downloads\\TestingFiles\\DemoHCLTestCaseScreens\\";
    private String ExcelFilePath = "C:\\Users\\om\\Downloads\\TestingFiles\\DemoHCLTestCases.xlsx";

    public HCLFunctions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void CheckLogin() throws IOException {
        if(!isElementPresent(By.linkText("Login"))) {
            driver.findElement(By.linkText("Log out")).click();
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void LoginRole(String Role) throws IOException {
        String UserName = null;
        String Password = null;

        if (Role.equals("Campaign-Admin")) {
            UserName = "campaign-admin";
            Password = "c@mpaign-@dmin";
        } else if (Role.equals("Admin")) {
            UserName = "admin";
            Password = "admin";
        }
        Login(UserName, Password);
    }

    public void Login(String UserName, String Password) throws IOException {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-name')]//input")).clear();
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-name')]//input")).sendKeys(UserName);
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-pass')]//input")).clear();
        driver.findElement(By.xpath("//div[contains(@class, 'form-item-pass')]//input")).sendKeys(Password);
        driver.findElement(By.xpath("//form[@id='user-login-form']//input[@type='submit']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='user-login-form']//input[@type='submit']")));
    }

    public void SetResultPass(String Role, String TestCase) throws Exception {
        // Get different information
        String[] ExcelValues = new String[6];
        // Role
        ExcelValues[0] = Role;
        // Filename
        String FileNameWithExtension = Thread.currentThread().getStackTrace()[2].getFileName();
        ExcelValues[1] = FileNameWithExtension.substring(0, FileNameWithExtension.indexOf("."));
        // Operation
        ExcelValues[2] = Thread.currentThread().getStackTrace()[2].getMethodName();
        // TestCase
        ExcelValues[3] = TestCase;
        // Current Url
        String CurrentUrl = driver.getCurrentUrl();
        ExcelValues[4] = CurrentUrl;
        // Take Screenshot
        File screen = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File(ScreensPath + SheetName + "\\" + ExcelValues[1] + "\\" + Role + "_" + TestCase + ".png"));
        // Screenshot FileName
        ExcelValues[5] = Role + "_" + TestCase;

        // Store information in excel.
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        // Set Cell Color
        XSSFCellStyle Style = ExcelWBook.createCellStyle();
        Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        Style.setFillForegroundColor(new XSSFColor(Color.GREEN));

        int RowNumber = ExcelWSheet.getLastRowNum() + 1;
        XSSFRow Row = ExcelWSheet.createRow(RowNumber);
        for (int i = 0; i < 6; i++) {
            XSSFCell Cell = Row.createCell(i);
            Cell.setCellValue(ExcelValues[i]);
            if (i == 3) {
                Cell.setCellStyle(Style);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public void SetResultFail(String Role, String TestCase, Exception e) throws Exception {
        // Get stackTrace information
        StringWriter SW = new StringWriter();
        PrintWriter PW = new PrintWriter(SW);
        e.printStackTrace(PW);
        String StackTrace = SW.toString();

        // Get different information
        String[] ExcelValues = new String[8];
        // Role
        ExcelValues[0] = Role;
        // Filename
        String FileNameWithExtension = Thread.currentThread().getStackTrace()[2].getFileName();
        ExcelValues[1] = FileNameWithExtension.substring(0, FileNameWithExtension.indexOf("."));
        // Operation
        ExcelValues[2] = Thread.currentThread().getStackTrace()[2].getMethodName();
        // Test Case
        ExcelValues[3] = TestCase;
        // Current Url
        String CurrentUrl = driver.getCurrentUrl();
        ExcelValues[4] = CurrentUrl;
        // Take Screenshot
        File screen = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File(ScreensPath + SheetName + "\\" + ExcelValues[1] + "\\" + Role + "_" + TestCase + ".png"));
        // Screenshot FileName
        ExcelValues[5] = Role + "_" + TestCase;

        // Error
        String[] Error = StackTrace.split("Command");
        ExcelValues[6] = Error[0];
        // Error Location
        String[] Lines = StackTrace.split("\n");
        String ErrorLocation = null;
        for (int i = 0; i < Lines.length; i++) {
            if (Lines[i].contains(ExcelValues[1])) {
                ErrorLocation = Lines[i];
                break;
            }
        }
        ExcelValues[7] = ErrorLocation;

        // Store information in excel.
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        // Set Cell Color
        XSSFCellStyle Style = ExcelWBook.createCellStyle();
        Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        Style.setFillForegroundColor(new XSSFColor(Color.RED));

        int RowNumber = ExcelWSheet.getLastRowNum() + 1;
        XSSFRow Row = ExcelWSheet.createRow(RowNumber);
        for (int i = 0; i < 8; i++) {
            XSSFCell Cell = Row.createCell(i);
            Cell.setCellValue(ExcelValues[i]);
            if (i == 3) {
                Cell.setCellStyle(Style);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
