package Screenshot;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.*;
import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by om on 1/7/2015.
 */
public class TakeScreenshot {
    ScreenshotFunctions func;
    String Structure = "Single";
    String Browser = "Firefox";
    String ScreenshotThrough = "Stack";
    String ReadTitleAndUrlFrom = "Excel";
    String Path = "TestingFiles\\Screenshots\\HCL\\";

    String SheetName = "Sheet1";
    String ExcelFilePath = "C:\\Users\\aman\\Downloads\\TestingFiles\\Screenshots\\";

    @Test
    @Parameters({"BaseUrl","SiteLevel","ExcelFile"})
    public void testFirst(String BaseUrl, String SiteLevel, String ExcelFile) throws Exception {
        func = new ScreenshotFunctions();
        // Create the directory for SiteLevel.
        File dir = new File(func.BasePath + Path + SiteLevel + ScreenshotThrough);
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Get Selections
        String[][] Selections = GetSelections();
        // Get Title and Url
        String[][] FilenameUrl = new String[2000][2];
        if (ReadTitleAndUrlFrom == "Excel") {
            FilenameUrl = ReadExcel(ExcelFile);
        } else if (ReadTitleAndUrlFrom == "Array") {
            FilenameUrl = ReadArray(BaseUrl);
        }

        if (ScreenshotThrough == "Driver") {
            func.TestOnChrome();
            func.OnBrowsersByWebDriver(FilenameUrl, SiteLevel, Path + SiteLevel + ScreenshotThrough, Browser, Structure);
        } else if (ScreenshotThrough == "Stack"){
            func.LoginBrowserStack(Selections);
            func.OnBrowsersByBrowserStack(FilenameUrl, SiteLevel, Path + SiteLevel + ScreenshotThrough, Selections.length, Structure, Selections);
            func.LogoutBrowserStack();
        }
    }

    private String[][] GetSelections() {
        return new String[][]{
                {"OS X", "Yosemite", "safari", "8.0", "macyos_safari_8.0"},
                {"Windows", "8.1", "firefox", "37.0", "win8.1_firefox_37.0"},
                {"Windows", "8.1", "chrome", "42.0", "win8.1_chrome_42.0"},
                {"OS X", "Mavericks", "safari", "7.0", "macmav_safari_7.0"},
                {"Windows", "8.1", "ie", "11.0", "win8.1_ie_11.0"},
                {"Windows", "7", "ie", "9.0", "win7_ie_9.0"},
        };
    }

    private String[][] ReadArray(String BaseUrl) {
        return new String[][]{{"About Us","" + BaseUrl + "/about-us"},
                {"About Us_About HCL Technologies","" + BaseUrl + "/about-us/about-hcl-technologies"},
                {"About Us_About HCL Enterprise","" + BaseUrl + "/about-us/about-hcl-enterprise"},
                {"About Us_Vision & Mission","" + BaseUrl + "/about-us/value-statements"},
                {"About Us_Leadership","" + BaseUrl + "/about-us/leadership/board-of-directors"},
                {"About Us_Leadership_Executive Profiles","" + BaseUrl + "/about-us/leadership/executive-profiles"},
                {"About Us_Leadership_Board of Directors","" + BaseUrl + "/about-us/leadership/board-of-directors"},
                {"About Us_Governance","" + BaseUrl + "/about-us/corporate-governance"},
                {"About Us_Governance_Governance Policies","" + BaseUrl + "/about-us/corporate-governance/governance-policies"},
                {"About Us_Alliances","" + BaseUrl + "/about-us/alliances"},
                {"About Us_Alliances_Microsoft Alliance","" + BaseUrl + "/about-us/microsoft-alliance"},
                {"About Us_Alliances_Microsoft Alliance_GamEdge","" + BaseUrl + "/about-us/gamedge"},
                {"About Us_Alliances_Microsoft Alliance_EmpoweRetail CRM for Retailers","" + BaseUrl + "/about-us/empoweretail-crm-retailers"},
                {"About Us_Alliances_Microsoft Alliance_FinEdge","" + BaseUrl + "/about-us/finedge"},
                {"About Us_Recognitions","" + BaseUrl + "/about-us/awards-recognitions"},
                {"About Us_Socially Responsible Business","" + BaseUrl + "/socially-responsible-business"}
        };
    }

    private String[][] ReadExcel(String ExcelFile) throws IOException, InvalidFormatException {
        FileInputStream FileInput = new FileInputStream(ExcelFilePath + ExcelFile);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        XSSFCell Cell = null;
        int RowNum = ExcelWSheet.getLastRowNum() + 1;
        int ColNum = ExcelWSheet.getRow(0).getLastCellNum();
        String[][] TitleUrl = new String[RowNum][ColNum];
        for (int i = 2; i < RowNum; i++){
            Row = ExcelWSheet.getRow(i);
            for (int j = 0; j < ColNum; j++) {
                Cell = Row.getCell(j);
                TitleUrl[i][j] = cellToString(Cell);
            }
        }
        return TitleUrl;
    }

    private String cellToString(XSSFCell cell){
        int type;
        Object result;
        type = cell.getCellType();

        switch (type) {
            case XSSFCell.CELL_TYPE_STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                result = cell.getCellFormula();
                break;
            default:
                throw new RuntimeException("No support for this type of cell");
        }
        return result.toString();
    }
}