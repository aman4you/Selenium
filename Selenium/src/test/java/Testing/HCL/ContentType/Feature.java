package Testing.HCL.ContentType;

import Testing.CommonFunction;
import Testing.HCL.HCLFunctions;
import Testing.HCL.HCLAbstract;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class Feature extends HCLAbstract {
    private String Title;
    private String PublishedDate;
    private String UploadImage;
    private String AlternateText;
    private String PictureTitle;
    private String PDF;
    private String Body;
    private String MetatagDescription;
    private String MetatagKeyword;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"title", "Today", FilesPath + "abc.jpg", "AlternateText", "PictureTitle", FilesPath + "bc.pdf", "body", "metaDesc", "metaKey", "all inputs are correct", "Admin"}
//                        {"Today", "-1", "70", "FB Total is negative"},
//                        {"Today", "181", "-1", "Twitter Total is negative"},
//                        {"Today", "178", "abc", "Twitter Total is alphabetic"},
//                        {"Today", "abc", "15","FB Total is alphabetic"},
//                        {"Yesterday", "160", "45", "When date of past"},
//                        {"Tomorrow", "160", "45", "When date of future"}
                }
        );
    }

    // Constructor will be used by the test runner to pass the parameters to the Excel_drive class instance.
    public Feature(String Title, String PublishedDate, String UploadImage, String AlternateText, String PictureTitle, String PDF, String Body, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.Title = Title;
        this.PublishedDate = PublishedDate;
        this.UploadImage = UploadImage;
        this.AlternateText = AlternateText;
        this.PictureTitle = PictureTitle;
        this.PDF = PDF;
        this.Body = Body;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Feature page
            driver.get(baseUrl + "/node/add/feature");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter Published Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-published-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-published-date')]//input")).sendKeys("Jan 28 2015");
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@type='file']")).sendKeys(UploadImage);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Upload PDF
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='file']")).sendKeys(PDF);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@value='Remove']")));
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Select Site Selection
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-site-section')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-site-section')]//ul", "[@data-option-array-index='");
            // Select Geo Presence Location
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-geo-presence-location')]//select");
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages messages--status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }

    @Test
    public void UpdateContent() throws Exception {
        try {
            // Click edit link of that content which type is Feature
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Feature
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Feature")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Title);
            // Enter Published Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-published-date')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-published-date')]//input")).sendKeys("Jan 28 2015");
            // Remove Image
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@value='Remove']")).click();
                // Wait for remove (Not locating through file because there is another file of same image type)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
            }
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@type='file']")).sendKeys(UploadImage);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[@value='Remove']")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-upload-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Remove PDf
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@value='Remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@value='Remove']")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='file']")));
            }
            // Upload PDF
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='file']")).sendKeys(PDF);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@value='Remove']")));
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
            // Select Site Selection
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-field-site-section')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-field-site-section')]//ul", "[@data-option-array-index='");
            // Select Geo Presence Location
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-geo-presence-location')]//select");
            // Click Save
            driver.findElement(By.id("edit-submit")).click();
            // Check content created or not
            driver.findElement(By.xpath("//div[@class='messages status']"));
            // Set Result Pass
            func.SetResultPass(Role, TestCase);
        } catch (Exception e) {
            // Set Result Fail
            func.SetResultFail(Role, TestCase, e);
        }
    }
}
