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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/24/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class IdeapreneurshipVideos extends HCLAbstract {
    private String Name;
    private String Description;
    private String EmployeePicture;
    private String AlternateText;
    private String Title;
    private String EmployeeVideo;

    /**
     * Define a Collection method that will return the collection of parameters to the parallelTestThroughJUnit class
     * by using the @Parameters annotation.
     */
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][]{
                        {"dummy", "i am dummy", FilesPath + "abc.jpg", "abc", "pic", FilesPath + "Andaman.mp4", "All inputs are correct", "Admin"}
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
    public IdeapreneurshipVideos(String Name, String Description, String EmployeePicture, String AlternateText, String Title, String EmployeeVideo, String TestCase, String Role) {
        this.Name = Name;
        this.Description = Description;
        this.EmployeePicture = EmployeePicture;
        this.AlternateText = AlternateText;
        this.Title = Title;
        this.EmployeeVideo = EmployeeVideo;
        this.TestCase = TestCase;
        this.Role = Role;
    }

    @Test
    public void CreateContent() throws Exception {
        try {
            // Move to ideapreneurship videos page
            driver.get(baseUrl + "/node/add/ideapreneurship-videos");
            // Enter employee name
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Name);
            // Enter description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Upload employee picture
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")).sendKeys(EmployeePicture);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='submit']")).click();
            // wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).sendKeys(Title);
            // Click select media
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-vid')]//a[@class='button launcher']")).click();
            // Upload employee video
            driver.findElement(By.xpath("//form[@id='media-add-upload']//div[contains(@class, 'form-item-files-upload')]//input[@type='file']")).sendKeys(EmployeeVideo);
            // Click submit
            driver.findElement(By.xpath("//form[@id='media-add-upload']//div[contains(@class, 'form-item-files-upload')]//input[@type='submit']")).click();
            // Click save
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
            // Click edit link of that content which type is Ideapreneurship Videos
            driver.get(baseUrl + "/admin/content");
            // Click edit link of that content which type is Ideapreneurship Videos
            List<WebElement> Types = driver.findElements(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr/td[3]"));
            String ContentType = null;
            int RowNumber = 0;
            for (int i = 0; i < Types.size(); i++) {
                ContentType = Types.get(i).getText();
                if (ContentType.equals("Ideapreneurship Videos")) {
                    RowNumber = i + 1;
                    driver.findElement(By.xpath("//table[contains(@class, 'sticky-enabled')]/tbody/tr[" + RowNumber + "]/td[8]/ul/li[1]/a")).click();
                    break;
                }
            }
            // Enter employee name
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'form-item-title')]//input")).sendKeys(Name);
            // Enter description
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'text-full')]/following-sibling::div[contains(@class, 'cke_browser_gecko')]//span[contains(@class, 'cke_button__source_label')]")).click();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-body')]//textarea[contains(@class, 'cke_editable_themed')]")).sendKeys(Description);
            // Remove Employee Picture
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")).click();
                // Wait for remove
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")));
            }
            // Upload employee picture
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='file']")).sendKeys(EmployeePicture);
            // Click upload
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[@type='submit']")).click();
            // wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'remove')]")));
            // Enter alternate text
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'alt')]")).sendKeys(AlternateText);
            // Enter title
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).clear();
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-pic')]//input[contains(@id, 'title')]")).sendKeys(Title);
            // Remove Media
            if (func.isElementPresent(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-vid')]//a[@class='button remove']"))) {
                driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-vid')]//a[@class='button remove']")).click();
            }
            // Click select media
            driver.findElement(By.xpath("//div[contains(@class, 'field-name-field-ideapreneursh-employee-vid')]//a[@class='button launcher']")).click();
            // Upload employee video
            driver.findElement(By.xpath("//form[@id='media-add-upload']//div[contains(@class, 'form-item-files-upload')]//input[@type='file']")).sendKeys(EmployeeVideo);
            // Click submit
            driver.findElement(By.xpath("//form[@id='media-add-upload']//div[contains(@class, 'form-item-files-upload')]//input[@type='submit']")).click();
            // Click save
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
