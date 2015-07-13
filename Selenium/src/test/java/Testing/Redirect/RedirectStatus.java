/**
 * Get status code
 * Get Redirect URI through HTTP response header ?
 */

package Testing.Redirect;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by aman on 7/8/2015.
 */
public class RedirectStatus {
    private String SheetName = "Crawl Errors";
    private String ExcelFilePath = "C:\\Users\\aman\\Downloads\\CrawlError.xlsx";

    @Test
    public void testFirst() throws Exception {
        FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(ExcelFile));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        String URL = null;
        int Status = 0;

        for (int i = 2 ; i < 643 ; i++) {
            Row = ExcelWSheet.getRow(i);
            URL = Row.getCell(0).getRichStringCellValue().getString();
            Status = getStatus(URL);
            writeData(Status, i);
        }
    }

    private int getStatus(String URL) throws InterruptedException, IOException {
        // Build the HTTP client.
        HttpClient client = HttpClientBuilder.create().build();
        // Build the Get request.
        HttpGet request = new HttpGet(URL);
        // Execute the Http request – return an instance of HttpResponse.
        HttpResponse response = client.execute(request);
        // Access the status line of the response, and implicitly the Status Code.
        int Status = response.getStatusLine().getStatusCode();

        return Status;
    }

    private void writeData(int Status, int i) throws IOException, InvalidFormatException {
        FileInputStream FileInput = new FileInputStream(ExcelFilePath);
        XSSFWorkbook ExcelWBook = new XSSFWorkbook(org.apache.poi.openxml4j.opc.Package.open(FileInput));
        XSSFSheet ExcelWSheet = ExcelWBook.getSheet(SheetName);

        XSSFRow Row = null;
        XSSFCell Cell4 = null;
        Row = ExcelWSheet.getRow(i);

        Cell4 = Row.createCell(4);
        Cell4.setCellType(Cell4.CELL_TYPE_NUMERIC);
        Cell4.setCellValue(Status);

        FileOutputStream FileOutput = new FileOutputStream(ExcelFilePath);
        ExcelWBook.write(FileOutput);
        FileOutput.close();
    }
}
