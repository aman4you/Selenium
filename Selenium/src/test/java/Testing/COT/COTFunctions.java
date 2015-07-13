package Testing.COT;

import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.*;

import static junit.framework.Assert.assertTrue;

/**
 * Created by om on 11/12/2014.
 */
public class COTFunctions {
    private WebDriver driver;
    public int timeoutOfOneElement = 15;
    public int timeoutOFAllElement = 10;
    public String baseUrl = "http://collegeontrackstg.prod.acquia-sites.com/";

    public COTFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public void CheckLogin() throws IOException {
        if(!isElementPresent(By.linkText("LOG IN"))) {
            driver.findElement(By.cssSelector("span.username-page-link")).click();
            driver.findElement(By.linkText("Sign Out")).click();
        }
    }

    private boolean isElementPresent(By by) {
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

        if (Role.equals("Parent")) {
            UserName = "";
            Password = "";
        } else if (Role.equals("Student")) {
            UserName = "g_1";
            Password = "student@COT";
        } else if (Role.equals("Teacher")) {
            UserName = "t_1";
            Password = "teacher@COT";
        } else if (Role.equals("SchoolAdmin")) {
            UserName = "g_schooladmin";
            Password = "schooladmin@COT";
        } else if (Role.equals("ContentManager")) {
            UserName = "amantest";
            Password = "password";
        }
        Login(UserName, Password);
    }

    public void Login(String UserName, String Password) throws IOException {
        driver.findElement(By.linkText("LOG IN")).click();
        driver.findElement(By.id("edit-name")).clear();
        driver.findElement(By.id("edit-name")).sendKeys(UserName);
        driver.findElement(By.id("edit-pass")).clear();
        driver.findElement(By.id("edit-pass")).sendKeys(Password);
        driver.findElement(By.xpath("//input[@class='form-submit']")).click();
    }

    public Boolean isTextPresent(String Result) throws IOException {
        if(Result.contains("results retrieved")) {
            return true;
        } else {
            return false;
        }
    }

    public void ClickRandomCheckboxes(By Locator) throws IOException {
        List<WebElement> Checkboxes = driver.findElements(Locator);
        int NumberOfCheckboxes = Checkboxes.size();
        int NumberOfCheckboxesToBeClicked = NumberOfCheckboxes/2;
        // Get random integer numbers as NumberOfCheckboxesToBeClicked from NumberOfCheckboxes.
        int[] RandomNumber = ManyRandomIntegerNumber(NumberOfCheckboxes, NumberOfCheckboxesToBeClicked);
        for (int i = 0; i < NumberOfCheckboxesToBeClicked; i++) {
            int ExactCheckbox = RandomNumber[i] - 1;
            Checkboxes.get(ExactCheckbox).click();
        }
    }

    public void SelectRandomElement(String MainLocator, String RestLocator) throws IOException {
        List<WebElement> Elements = driver.findElements(By.xpath(MainLocator));
        int NumberOfElements = Elements.size();
        if(NumberOfElements > 0) {
            // Access Random Element
            int RandomElement = RandomIntegerNumber(NumberOfElements);
            // Click Random Element
            driver.findElement(By.xpath(MainLocator + "[" + RandomElement + "]" + RestLocator)).click();
        }
    }

    public void SelectRandomChosenOption(String CountItem, String ClickChosen, String SelectItem) throws IOException {
        List<WebElement> SelectListOptions = driver.findElements(By.xpath(CountItem));
        int OptionsNumber = SelectListOptions.size();
        if(OptionsNumber > 0) {
            // Access Random Option
            int RandomOption = RandomIntegerNumber(OptionsNumber) - 1;
            // Select Random Option
            driver.findElement(By.xpath(ClickChosen)).click();
            driver.findElement(By.xpath(SelectItem + RandomOption + "']")).click();
        }
    }

    public void SelectRandomSelectListOption(String Locator) throws IOException {
        // Check Select List Options exist or not
        List<WebElement> SelectListOptions = driver.findElements(By.xpath(Locator + "/option"));
        int OptionsNumber = SelectListOptions.size();
        int TotalOptionsNumber = OptionsNumber - 1;
        if(TotalOptionsNumber > 0) {
            // Access Random Option
            int RandomOption = RandomIntegerNumber(TotalOptionsNumber);
            // Select Random Option
            new Select(driver.findElement(By.xpath(Locator))).selectByIndex(RandomOption);
        }
    }

    public void SelectRandomSelectListOptionWhichInGroup(String Locator) throws IOException {
        WebElement RoteElement = driver.findElement(By.xpath(Locator));
        List<WebElement> NodeElements = null;
        // Make list of elements that have tagName option in RoteElement.
        NodeElements = RoteElement.findElements(By.tagName("option"));
        // Count number of elements
        int NumberOfElement = NodeElements.size();
        if (NumberOfElement > 0) {
            // Access Random Element
            int RandomNumber = RandomIntegerNumber(NumberOfElement) - 1;
            // Select Random Element
            NodeElements.get(RandomNumber).click();
        }
    }

    public int RandomIntegerNumberBetweenRange(int Max, int Min) throws IOException {
        Random random = new Random();
        int number = random.nextInt(Max - Min) + 6;
        return number;
    }

    public int[] ManyRandomIntegerNumber(int Max, int NumberOfInteger) throws IOException {
        Random random = new Random();
        // HashSet class used to get unique values.
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < NumberOfInteger) {
            set.add(random.nextInt(Max) + 1);
        }
        // Assign Integer object value to primitive type int variable.
        int[] number = new int[set.size()];
        int i = 0;
        for (Integer val : set) {
            number[i++] = val;
        }
        return number;
    }

    public int RandomIntegerNumber(int Max) throws IOException {
        Random random = new Random();
        // If Max have 5 than random number return from 0 to 4, So add 1.
        int number = random.nextInt(Max) + 1;
        return number;
    }

    public Double RandomDecimalNumber(int Max) throws IOException {
        Double DecimalNumber = .5;
        Random random = new Random();
        Double number = Double.valueOf(random.nextInt(Max) + 1) * DecimalNumber;
        return number;
    }

    public String[] RandomWords(int NumberOfWords) throws IOException {
        String[] randomStrings = new String[NumberOfWords];
        Random random = new Random();
        for(int i = 0; i < NumberOfWords; i++) {
            // Length of array get through nextInt() method.
            char[] word = new char[random.nextInt(8)];
            for(int j = 0; j < word.length; j++) {
                // Character for word get through Typecast.
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public void StoreException(Exception e, String TestCase) throws Exception {
        // Get stackTrace information
        StringWriter SW = new StringWriter();
        PrintWriter PW = new PrintWriter(SW);
        e.printStackTrace(PW);
        String StackTrace = SW.toString();

        // Get different exception information
        String[] ExcelValues = new String[4];
        // Test Case
        ExcelValues[0] = TestCase;
        // Current Url
        String CurrentUrl = driver.getCurrentUrl();
        ExcelValues[1] = CurrentUrl;
        // Error
        String[] Error = StackTrace.split("Command");
        ExcelValues[2] = Error[0];
        // Error Location
        String TopPackageName = TestCase.substring(0, TestCase.indexOf("."));
        String[] Lines = StackTrace.split("\n");
        String ErrorLocation = null;
        for (int i = 0; i < Lines.length; i++) {
            if (Lines[i].contains(TopPackageName)) {
                ErrorLocation = Lines[i];
                break;
            }
        }
        ExcelValues[3] = ErrorLocation;

        // Store exception information in excel.
        FileInputStream ExcelFile = new FileInputStream("C:\\Users\\om\\Downloads\\NewInfo\\COTTestCases.xlsx");
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet("ErrorList");

        int RowNumber = ExcelWSheet.getLastRowNum() + 1;
        XSSFRow Row = ExcelWSheet.createRow(RowNumber);
        for (int i = 0; i < 4; i++) {
            XSSFCell Cell = Row.createCell(i);
            Cell.setCellValue(ExcelValues[i]);
        }

        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\om\\Downloads\\NewInfo\\COTTestCases.xlsx");
        ExcelWBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
