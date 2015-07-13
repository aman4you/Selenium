package Framework.FieldDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by om on 2/18/2015.
 */
public class UIOperation {
    WebDriver driver;
    WebDriverWait wait;
    String FilesPath = "C:\\Users\\aman\\Downloads\\TestingFiles\\Files\\";

    public UIOperation(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void Perform(String Label, String FieldType, String Locator, String Value) throws Exception {
        if (FieldType.equalsIgnoreCase("URL")) {
            // Go To Url
            driver.get(Value);
        }
        else if (FieldType.equalsIgnoreCase("TEXTAREA")) {
            // Enter Text
            driver.findElement(By.cssSelector(Locator + " textarea")).clear();
            driver.findElement(By.cssSelector(Locator + " textarea")).sendKeys(Value);
        }
        else if (FieldType.equalsIgnoreCase("TAXONOMY BUTTON")) {
            // Click Taxonomy Button
            driver.findElement(By.cssSelector(Locator + " input.add-to-dropbox.form-submit")).click();
        }
        else if (FieldType.equalsIgnoreCase("SUBMIT")) {
            // Click Submit
            driver.findElement(By.cssSelector(Locator + " input[class='form-submit']")).click();
            String Result = CheckResult();
            if (Result.equalsIgnoreCase("Incorrect Input")) {
                throw new Exception(Result);
            }
        }
        else if (FieldType.equalsIgnoreCase("LINK") || FieldType.equalsIgnoreCase("Button")) {
            // Click Link or Button
            driver.findElement(By.cssSelector(Locator)).click();
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("UPLOAD FILE".replace(" ", ""))) {
            // Upload File
            driver.findElement(By.cssSelector(Locator + " input[type='file']")).sendKeys(FilesPath + Value);
            // Click Upload
            driver.findElement(By.cssSelector(Locator + " input[type='submit']")).click();
            // Wait for upload
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Locator + " input[id*='remove']")));
        }
        else if (FieldType.equalsIgnoreCase("CKEDITOR")) {
            // Enter Description
            driver.findElement(By.cssSelector(Locator + " span[class*='cke_button__source_label']")).click();
            driver.findElement(By.cssSelector(Locator + " textarea[class*='cke_editable_themed']")).clear();
            driver.findElement(By.cssSelector(Locator + " textarea[class*='cke_editable_themed']")).sendKeys(Value);
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("CKEDITOR SUMMARY".replace(" ", ""))) {
            // Enter Description Summary
            driver.findElement(By.cssSelector(Locator + " textarea[class*='text-full'] + div[class*='cke_browser_gecko'] span[class*='cke_button__source_label']")).click();
            driver.findElement(By.cssSelector(Locator + " textarea[class*='cke_editable_themed']")).clear();
            driver.findElement(By.cssSelector(Locator + " textarea[class*='cke_editable_themed']")).sendKeys(Value);
        }
        else if (FieldType.equalsIgnoreCase("TEXTFIELD")) {
            // Enter Text
            driver.findElement(By.cssSelector(Locator + " input")).clear();
            driver.findElement(By.cssSelector(Locator + " input")).sendKeys(Value);
        }
        else if (FieldType.equalsIgnoreCase("CHECKBOX")) {
            List<WebElement> Checkboxes = driver.findElements(By.cssSelector(Locator + " input[type='checkbox']"));
            int NumberOfCheckboxes = Checkboxes.size();
            if (NumberOfCheckboxes == 1) {
                Checkboxes.get(0).click();
            }
            int NumberOfCheckboxesToBeClicked = NumberOfCheckboxes/2;
            // Get random integer numbers as NumberOfCheckboxesToBeClicked from NumberOfCheckboxes.
            int[] RandomNumber = ManyRandomIntegerNumber(NumberOfCheckboxes, NumberOfCheckboxesToBeClicked);
            for (int i = 0; i < NumberOfCheckboxesToBeClicked; i++) {
                int ExactCheckbox = RandomNumber[i] - 1;
                Checkboxes.get(ExactCheckbox).click();
            }
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("Radio Button".replace(" ", ""))) {
            List<WebElement> RadioButtons = driver.findElements(By.cssSelector(Locator + " input"));
            int NumberOfRadioButtons = RadioButtons.size();
            // Access Random radio button
            int RandomElement = RandomIntegerNumber(NumberOfRadioButtons) - 1;
            // Click Random Element
            RadioButtons.get(RandomElement).click();
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("SINGLE SELECT".replace(" ", "")) || FieldType.replace(" ", "").equalsIgnoreCase("MULTIPLE SELECT".replace(" ", ""))) {
            // Select Item
            List<WebElement> SelectListOptions = driver.findElements(By.cssSelector(Locator + " select option"));
            int NumberOfOptions = SelectListOptions.size();
            int TotalOptions = NumberOfOptions - 1;
            // Access Random Option
            int RandomOption = RandomIntegerNumber(TotalOptions);
            // Select Random Option
            new Select(driver.findElement(By.cssSelector(Locator + " select"))).selectByIndex(RandomOption);
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("SINGLE CHOSEN SELECT".replace(" ", ""))) {
            // Click chosen select list
            driver.findElement(By.cssSelector(Locator + " a")).click();
            // Check chosen select list option exist or not
            List<WebElement> SelectListOptions = driver.findElements(By.cssSelector(Locator + " ul[class='chosen-results'] li"));
            int NumberOfOptions = SelectListOptions.size();
            // Access Random Option
            int RandomOption = RandomIntegerNumber(NumberOfOptions) - 1;
            // Select Random Option
            driver.findElement(By.cssSelector(Locator + " ul[class='chosen-results'] li" + "[data-option-array-index='" + RandomOption + "']")).click();
        }
        else if (FieldType.replace(" ", "").equalsIgnoreCase("MULTIPLE CHOSEN SELECT".replace(" ", ""))) {
            // Click chosen select list
            driver.findElement(By.cssSelector(Locator + " ul")).click();
            // Check chosen select list option exist or not
            List<WebElement> SelectListOptions = driver.findElements(By.cssSelector(Locator + " ul[class='chosen-results'] li"));
            int NumberOfOptions = SelectListOptions.size();
            // Access Random Option
            int RandomOption = RandomIntegerNumber(NumberOfOptions) - 1;
            // Select Random Option
            driver.findElement(By.cssSelector(Locator + " ul[class='chosen-results'] li" + "[data-option-array-index='" + RandomOption + "']")).click();
        }
        else {
            throw new Exception("Incorrect Field Type");
        }
    }

    private String CheckResult() throws IOException {
        if(isElementPresent(By.cssSelector("div[class='messages error']"))) {
            return "Incorrect Input";
        } else {
            return "Pass";
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

    public int RandomIntegerNumber(int Max) throws IOException {
        Random random = new Random();
        // If Max have 5 than random number return from 0 to 4, So add 1.
        int number = random.nextInt(Max) + 1;
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
}