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
public class GeoPresence extends HCLAbstract {
    private String Title;
    private String Body;
    private String Image;
    private String AlternateText;
    private String PictureTitle;
    private String LocationOnMap;
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
                        {"dummy", "Desc", FilesPath + "abc.jpg", "AltText", "PicTitle", "location", "metaDesc", "metaKey", "all inputs are correct", "Admin"}
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
    public GeoPresence(String Title, String Body, String Image, String AlternateText, String PictureTitle, String LocationOnMap, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.Title = Title;
        this.Body = Body;
        this.Image = Image;
        this.AlternateText = AlternateText;
        this.PictureTitle = PictureTitle;
        this.LocationOnMap = LocationOnMap;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Geo Presence page
            driver.get(baseUrl + "/node/add/geo-presence");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='file']")).sendKeys(Image);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Location On Map
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-gmap-macro')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-gmap-macro')]//textarea")).sendKeys(LocationOnMap);
            // Select Marker Type
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-geo-presence-marker-type')]//select");
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
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
            // Click edit link of that content which type is Geo Presence
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Geo Presence
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Geo Presence")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Enter Body
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Body);
            // Remove Image
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'remove')]"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'remove')]")).click();
                // Wait for remove (Not locating through file because there is another file of same image type)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("throbber")));
            }
            // Upload Image
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='file']")).sendKeys(Image);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter picture title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-uploaded-image')]//input[contains(@id, 'title')]")).sendKeys(PictureTitle);
            // Enter Location On Map
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-gmap-macro')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-gmap-macro')]//textarea")).sendKeys(LocationOnMap);
            // Select Marker Type
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-geo-presence-marker-type')]//select");
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
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
