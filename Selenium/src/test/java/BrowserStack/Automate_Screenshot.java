package BrowserStack;

/**
 * Created by om on 12/6/13.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

public class Automate_Screenshot {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @org.junit.Test
    public void testFirst() throws Exception {
        String filename = null;
        String url = null;

        String file_url[][] = new String[][]{
                {"HCL_Global_Meet","http://hcltech.com/i-have-an-idea/hcl-global-meet"},
                {"Enterprise_Application","http://hcltech.com/enterprise-application-services"},
                {"Media_Entertainment","http://hcltech.com/media-entertainment"},
                {"Contact_Us_Analyst","http://hcltech.com/contact-us/analyst"},
                {"Retail_Customer","http://hcltech.com/retail-consumer"}
        };

        String list[] = new String[2];
        for(int i=0; i<5; i++) {
            for(int j=0; j<2; j++) {
                list[j]  = file_url[i][j];
            }
            filename = list[0];
            url = list[1];
            output(filename, url);
        }
    }

    private void output(String filename, String url) throws IOException {
        // Define Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "IE");
        caps.setCapability("browser_version", "10");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "version1");
        caps.setCapability("project", "Screenshot");

        // Run Test on Browserstack
        driver = new RemoteWebDriver(new URL("http://amangoel9:244kSwskGEhtijmSnxHQ@hub.browserstack.com/wd/hub"), caps);
        driver.manage().window().setSize(new Dimension(1200, 1200));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url);

//        File screen = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screen, new File("C:\\Users\\om\\Downloads\\Programs\\etc\\Screens\\" + filename + ".png"));

        // Take Screenshot
        driver = new Augmenter().augment(driver);
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File("C:\\Users\\om\\Downloads\\abc\\" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
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

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}