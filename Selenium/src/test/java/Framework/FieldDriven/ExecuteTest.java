package Framework.FieldDriven;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 2/18/2015.
 */
public class ExecuteTest {
    UIOperation func;
    WebDriver driver;
    WebDriverWait wait;
    int timeoutOfOneElement = 10;
    int timeoutOFAllElement = 15;
    String SheetName = "Sheet1";
    String ExcelFilePath = "C:\\Users\\aman\\Downloads\\TestingFiles\\FieldDriven\\Timehub.xlsx";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aman\\Downloads\\Programs\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, timeoutOfOneElement);
        func = new UIOperation(driver, wait);
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutOFAllElement, TimeUnit.SECONDS);
    }

    @Test
    public void testLogin() throws Exception {
        // Read excel file
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        // Declare variables to utilize
        XSSFRow Row = null;
        XSSFCell Cell = null;
        XSSFRow TestCaseRow = null;
        XSSFCell TestCaseCell= null;
        int TestCaseFirstRow = 0;
        int TestCaseLastRow = 0;
        int RowNum = ExcelWSheet.getLastRowNum() + 1;
        int ColNum = ExcelWSheet.getRow(0).getLastCellNum();

        // Read excel rows
        for (int i = 1 ; i < RowNum ; i++) {
            Row = ExcelWSheet.getRow(i);
            // Check value in row exist or not
            if (Row != null) {
                Cell = Row.getCell(0);
                // Check value in first column exist or not
                if (Cell != null) {
                    // Get first row number for further use
                    TestCaseFirstRow = i;
                    String[][] Data = new String[RowNum][ColNum];
                    //  Get test case rows to create 2-D array
                    for (int j = i ; j < RowNum ; j++) {
                        TestCaseRow = ExcelWSheet.getRow(j);
                        // Read row until null not found
                        if (TestCaseRow != null) {
                            for (int k = 1 ; k < ColNum ; k++) {
                                TestCaseCell = TestCaseRow.getCell(k);
                                // Fill excel data in 2-D array
                                if (TestCaseCell != null) {
                                    Data[j][k] = cellToString(TestCaseCell);
                                } else {
                                    Data[j][k] = "";
                                }
                            }
                            // Get Last row number for further use
                            TestCaseLastRow = j;
                        } else {
                            // Assign next test case row number
                            i = j;
                            break;
                        }
                    }
                    // Read 2-D array row one by one
                    for (int RowNumber = TestCaseFirstRow ; RowNumber <= TestCaseLastRow ; RowNumber++) {
                        try {
                            // Call perform function to execute test case row
                            func.Perform(Data[RowNumber][1], Data[RowNumber][2], Data[RowNumber][3], Data[RowNumber][4]);
                            // Change color of cell of that row where previously error occurred
                            ChangeCellColor("Pass", RowNumber, "");
                        } catch (NoSuchElementException e) {
                            // Change color of cell of that row in which error occurred
                            ChangeCellColor("Fail", RowNumber, "Incorrect Locator");
                            // To not execute further rows of test case
                            break;
                        } catch (Throwable t) {
                            if (t.getMessage() == "Incorrect Field Type") {
                                // Change color of cell of that row in which error occurred
                                ChangeCellColor("Fail", RowNumber, "Incorrect Field Type");
                            } else if (t.getMessage() == "Incorrect Input") {
                                // Change color of cell of that row in which error occurred
                                ChangeCellColor("Fail", RowNumber, "Incorrect Input");
                            }
                            // To not execute further rows of test case
                            break;
                        }
                    }
                }
            }
        }
    }

    public static String cellToString(Cell cell){
        Object Value;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                Value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Value = cell.getDateCellValue();
                } else {
                    Value = (int) cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                Value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                Value = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK:
                Value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_ERROR:
                Value = cell.getErrorCellValue();
                break;
            default:
                throw new RuntimeException("No support for this type of cell");
        }
        return Value.toString();
    }

    private void ChangeCellColor(String TestCaseResult, int m, String Error) throws IOException, InvalidFormatException {
        // Read excel file
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        // Set Cell Color
        XSSFCellStyle Style = ExcelWBook.createCellStyle();
        if (TestCaseResult == "Pass") {
            Style.setFillPattern(XSSFCellStyle.NO_FILL);
        } else {
            Style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            Style.setFillForegroundColor(new XSSFColor(Color.RED));
        }

        XSSFRow Row = null;
        XSSFCell Cell = null;
        Row = ExcelWSheet.getRow(m);
        Cell = Row.getCell(5);
        if (Cell == null)
            Cell = Row.createCell(5);
        Cell.setCellValue(Error);
        Cell.setCellStyle(Style);

        // Write into excel file
        FileOutputStream FileOutput = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(FileOutput);
        FileOutput.close();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
