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
public class AnalystPDF extends HCLAbstract {
    private String Title;
    private String StartDate;
    private String EndDate;
    private String ShortDescription;
    private String Description;
    private String PDF;
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
                        {"title", "Today", "Tomorrow", "ShortDesc", "Desc", FilesPath + "bc.pdf", "metaDesc", "metaKey", "all inputs are correct", "Admin"}
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
    public AnalystPDF(String Title, String StartDate, String EndDate, String ShortDescription, String Description, String  PDF, String MetatagDescription, String MetatagKeyword, String TestCase, String Role) {
        this.Title = Title;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.ShortDescription = ShortDescription;
        this.Description = Description;
        this.PDF = PDF;
        this.MetatagDescription = MetatagDescription;
        this.MetatagKeyword = MetatagKeyword;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to Analyst PDF page
            driver.get(baseUrl + "/node/add/analyst-pdfs");
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Click Show End Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[@class='date-float']//input")).click();
            // Enter Start Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'start-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'start-date-wrapper')]//input")).sendKeys("06.01.2015");
            // Enter End Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'end-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'end-date-wrapper')]//input")).sendKeys("22.01.2015");
            // Enter Short Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ShortDescription);
            // Enter Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Select Service Lines
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-selects-0"))).selectByVisibleText("HCL AXON");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Industries
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-selects-0"))).selectByVisibleText("Professional Services");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Analyst PDF
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-taxonomy-vocabulary-7')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-taxonomy-vocabulary-7')]//ul", "[@data-option-array-index='");
            // Upload PDF
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='file']")).sendKeys(PDF);
            // Click Upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-pdf-upload')]//input[@value='Remove']")));
            // Select Location
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-location')]//select");
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
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
            // Click edit link of that content which type is Analyst PDF
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Analyst PDF
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Analyst pdfs")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter Title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-title-field')]//input")).sendKeys(Title);
            // Select Language
            func2.SelectRandomSelectListOption("//div[contains(@class, 'form-item-language')]//select");
            // Check Show End Date clicked or not
            if (!func2.isAttributePresent("//div[contains(@class, 'field-name-field-quarter-period')]//div[@class='date-float']//input", "checked")) {
                // Click Show End Date
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[@class='date-float']//input")).click();
            }
            // Enter Start Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'start-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'start-date-wrapper')]//input")).sendKeys("06.01.2015");
            // Enter End Date
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'end-date-wrapper')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-quarter-period')]//div[contains(@class, 'end-date-wrapper')]//input")).sendKeys("22.01.2015");
            // Enter Short Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-short-desc')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(ShortDescription);
            // Enter Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Select Service Lines
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-selects-0"))).selectByVisibleText("HCL AXON");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-2-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Industries
            new Select(driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-selects-0"))).selectByVisibleText("Professional Services");
            // Click Add
            driver.findElement(By.id("edit-taxonomy-vocabulary-3-und-hierarchical-select-dropbox-add")).click();
            // Wait for add
            Thread.sleep(5000);
            // Select Analyst PDF
            func2.SelectRandomChosenOption("//div[contains(@class, 'field-name-taxonomy-vocabulary-7')]//ul[@class='chosen-results']/li", "//div[contains(@class, 'field-name-taxonomy-vocabulary-7')]//ul", "[@data-option-array-index='");
            // Remove PDF
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
            // Select Location
            func2.SelectRandomSelectListOption("//div[contains(@class, 'field-name-field-location')]//select");
            // Enter Metatag Description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-description')]//textarea")).sendKeys(MetatagDescription);
            // Enter Metatag Keyword
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-node-metatag-keywords')]//textarea")).sendKeys(MetatagKeyword);
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
