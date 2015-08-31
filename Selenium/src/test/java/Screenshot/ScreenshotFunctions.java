package Screenshot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.StringUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by om on 1/7/2015.
 */
public class ScreenshotFunctions {
    private String Password = "myfriends";
    private String Username = "mukesh.agarwal@innoraft.com";
    public String BasePath = "C:\\Users\\aman\\Downloads\\";
    private WebDriver driver;
    private WebDriverWait wait;
    private int timeoutOfOneElement = 900;
    private int timeoutOFAllElement = 20;
    private String ChromeDriverPath = "C:\\Users\\aman\\Downloads\\Programs\\Selenium\\chromedriver.exe";
    private String IEDriverPath = "C:\\Users\\aman\\Downloads\\Programs\\Selenium\\IEDriverServer.exe";
    private int Timeout = 0;

    // To run test on Chrome browser
    public void TestOnChrome() throws IOException {
        // Disable the "unsupported flag" prompt, it affect the window size.
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(timeoutOFAllElement, TimeUnit.SECONDS);
    }

    // To run test on Firefox browser
    public void TestOnFirefox() throws IOException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutOFAllElement, TimeUnit.SECONDS);
    }

    // To run test on Internet Explorer browser
    public void TestOnInternetExplorer() throws IOException {
        System.setProperty("webdriver.ie.driver", IEDriverPath);
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeoutOFAllElement, TimeUnit.SECONDS);
    }

    // To take screenshots through webdriver
    public void OnBrowsersByWebDriver(String[][] FilenameUrl, String SiteLevel, String path, String Browser, String Structure) throws IOException, InterruptedException {
        String Filename = null;
        String Url = null;

        String GetFilenameUrl[] = new String[2];
        for(int i = 1; i < FilenameUrl.length; i++) {
            for(int j = 0; j < 2; j++) {
                GetFilenameUrl[j]  = FilenameUrl[i][j];
            }
            Filename = GetFilenameUrl[0].replace(" ", "").replace("&", "and").replace("_","-");
            Url = GetFilenameUrl[1];

            driver.get(Url);

            // Wait for page to be fully loaded
            Thread.sleep(10000);

            // Take Screenshot
            File Screen = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

            // To get the width of image.
            BufferedImage readImage = ImageIO.read(Screen);
            int Width = readImage.getWidth();

            // Create Directory according to structure.
            String Directory = null;
            if (Structure == "Group") {
                Directory = Filename;
            } else if (Structure == "Single") {
                Directory = Browser;
            }

            // Create filename according to screenshot comparision criteria
            String NewFilename = Width + "_" + Filename + "_" + Browser + SiteLevel;
            // Save screenshot
            FileUtils.copyFile(Screen, new File(BasePath + path + "\\" + Directory + "\\" + NewFilename + ".png"));
        }
    }

    // To login the BrowserStack site.
    public void LoginBrowserStack(String[][] Selections) throws IOException {
        TestOnChrome();

        // Wait for particular element to load.
        wait = new WebDriverWait(driver, timeoutOfOneElement);

        // Reach at snapshot page.
        driver.get("http://www.browserstack.com/users/sign_in");
        driver.findElement(By.id("user_email_login")).clear();
        driver.findElement(By.id("user_email_login")).sendKeys(Username);
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys(Password);
        driver.findElement(By.id("user_submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Account")));
        driver.get("https://www.browserstack.com/screenshots");

        // Select browsers
        SetSelectionsOnBrowserStack(Selections);
    }

    // Select the browsers on that screenshots should be taken.
    private void SetSelectionsOnBrowserStack(String[][] Selections) throws IOException {
        // Deselect these browsers, which are in selected state by default.
        List<WebElement> DeselectBrowser = driver.findElements(By.xpath("//a[contains(@class, 'version sel')]"));
        for (int k = 0; k < DeselectBrowser.size(); k++) {
            DeselectBrowser.get(k).click();
        }

        String OS = null;
        String Browser = null;
        String OS_Version = null;
        String Browser_Version = null;

        // Select Browsers on which screenshot should be taken.
        String GetSelection[] = new String[4];
        for(int i = 0; i < Selections.length; i++) {
            for(int j = 0; j < 4; j++) {
                GetSelection[j]  = Selections[i][j];
            }
            OS = GetSelection[0];
            OS_Version = GetSelection[1];
            Browser = GetSelection[2];
            Browser_Version = GetSelection[3];
            driver.findElement(By.xpath("//a[@os='" + OS + "'][@os_version='" + OS_Version + "'][@browser='" + Browser + "'][@browser_version='" + Browser_Version + "']")).click();
        }
    }

    // To take screenshots through BrowserStack
    public void OnBrowsersByBrowserStack(String[][] FilenameUrl, String SiteLevel, String Path, int NumberOFSelection, String Structure, String[][] Selections) throws IOException, InterruptedException {
        String Filename = null;
        String Url = null;
        String GetFilenameUrl[] = new String[2];

        for(int i = 1; i < FilenameUrl.length; i++) {
            for(int j = 0; j < 2; j++) {
                GetFilenameUrl[j]  = FilenameUrl[i][j];
            }
            Filename = GetFilenameUrl[0].replace(" ", "").replace("&", "and").replace("_","-");
            Url = GetFilenameUrl[1];

            // Take snapshot.
            driver.findElement(By.id("screenshots")).clear();
            driver.findElement(By.id("screenshots")).sendKeys(Url);
            Thread.sleep(4000);
            driver.findElement(By.id("btnSnapshot")).click();

            // Driver will wait until text 'ZIPPING' not present at element by id 'zipper'.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("zipper"), "ZIPPING"));

            // Click download link to save file.
            List<WebElement> DownloadLink = driver.findElements(By.xpath("//a[contains(@class, 'icon-sp-dl')]"));
            for (int k = 0; k < DownloadLink.size(); k++) {
                DownloadLink.get(k).click();
                Thread.sleep(4000);
            }

            // Check download process finish.
            File[] Files = CheckDownloadFinish(NumberOFSelection, Selections);

            // Rename image files.
            RenameFiles(Filename, Files, SiteLevel);

            // Cut all files and paste into new folder.
            CutPasteFiles(Filename, Path, Structure, SiteLevel);

            // Click arrow symbol ^ to click screenshot button.
            if (Timeout == 1) {
                driver.findElement(By.cssSelector("a.sp-more-arrow.up")).click();
                Thread.sleep(4000);
                SetSelectionsOnBrowserStack(Selections);
                Timeout = 0;
            } else {
                driver.findElement(By.cssSelector("a.sp-more-arrow.up")).click();
            }
        }
    }

    // Wait for download completion of screenshots.
    private File[] CheckDownloadFinish(int NumberOfSelection, String[][] Selections) throws IOException, InterruptedException {
        File[] ImageFiles;
        File Dir = new File(BasePath);

        // Loop will continue till the download process of files complete.
        for (; ;) {
            // Wait for Screenshot to download.
            Thread.sleep(30000);

            // List files that have extension “.crdownload”.
            FileFilter CrdownloadFilter = new WildcardFileFilter("*.crdownload");
            File[] CrdownloadFiles = Dir.listFiles(CrdownloadFilter);
            if (CrdownloadFiles.length == 0) {
                // List files that have extension “.png” and “.jpg”.
                String FilterWildcards[] = {"*.jpg", "*.png"};
                FileFilter ImageFilter = new WildcardFileFilter(FilterWildcards);
                ImageFiles = Dir.listFiles(ImageFilter);

                // Check all Screenshot download or not.
                if (ImageFiles.length == NumberOfSelection) {
                    break;
                } else {
                    Timeout = 1;
                    TakeTimeoutScreenshot(Selections, ImageFiles);
                }
            }

            System.out.println("Waiting");
        }
        return ImageFiles;
    }

    // Download Timeout Screenshot
    private void TakeTimeoutScreenshot(String[][] Selections, File[] ListOfFiles) throws IOException, InterruptedException {
        String[] FileNames = new String[ListOfFiles.length];

        // Get detail of Screenshot those Process Finish.
        for (int i = 0; i < ListOfFiles.length; i++) {
            FileNames[i] = FilenameUtils.removeExtension(ListOfFiles[i].getName());
        }

        int k = 0;
        int flag = 0;
        int RemainingScreenshot = Selections.length - FileNames.length;

        // List Screenshot those Process not Finish.
        String[][] TimeoutScreenshot = new String[RemainingScreenshot][4];
        for (int i = 0; i < Selections.length; i++) {
            for (int j = 0; j < FileNames.length; j++) {
                if (FileNames[j].equals(Selections[i][4])) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                TimeoutScreenshot[k] = Selections[i];
                k++;
            }
            flag = 0;
        }

        // Reload Page
        driver.navigate().refresh();

        // Click arrow symbol(^) to click screenshot button .
        driver.findElement(By.cssSelector("a.sp-more-arrow.up")).click();
        Thread.sleep(4000);

        // Select Timeout browsers
        SetSelectionsOnBrowserStack(TimeoutScreenshot);

        // Take Timeout Screenshot
        driver.findElement(By.id("btnSnapshot")).click();

        // Driver will wait until text 'ZIPPING' not present at element by id 'zipper'.
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("zipper"), "ZIPPING"));

        // Click download link to save file.
        List<WebElement> DownloadLink = driver.findElements(By.xpath("//a[contains(@class, 'icon-sp-dl')]"));
        for (int i = 0; i < DownloadLink.size(); i++) {
            DownloadLink.get(i).click();
        }
    }

    // Change name of screenshots according to screenshot comparison criteria.
    private void RenameFiles(String Filename, File[] Files, String SiteLevel) throws IOException {
        for (int i = 0 ; i < Files.length ; i++){
            String FileWithoutExtension = FilenameUtils.removeExtension(Files[i].getName());
            String[] SplitFilename = FileWithoutExtension.split("_");
            String[] WindowBrowserVersion = new String[3];
            for (int j = 0; j < SplitFilename.length ; j++) {
                WindowBrowserVersion[j] = SplitFilename[j];
            }

            // To get the width of image.
            BufferedImage readImage = ImageIO.read(Files[i]);
            int Width = readImage.getWidth();

            Files[i].renameTo(new File(BasePath + Width + "_" + Filename + "_" + SiteLevel + "-" + WindowBrowserVersion[0] + "-" + WindowBrowserVersion[1] + "-" + WindowBrowserVersion[2] + ".png"));
        }
    }

    // Cut the screenshots files and paste into created directory or existing directory and replace the existing screenshots.
    private void CutPasteFiles(String Filename, String Path, String Structure, String SiteLevel) throws IOException, InterruptedException {
        // List files that have extension “.png” and “.jpg”.
        File[] ListOfFiles;
        File Dir1 = new File(BasePath);
        String FilterWildcards[] = {"*.jpg", "*.png"};

        FileFilter ImageFilter = new WildcardFileFilter(FilterWildcards);
        ListOfFiles = Dir1.listFiles(ImageFilter);

        for (int i = 0 ; i < ListOfFiles.length ; i++){
            // To construct the path of file.
            Path Original = Paths.get(BasePath + ListOfFiles[i].getName());

            // Create Directory according to structure.
            if (Structure == "Single") {
                File Dir2 = new File(BasePath + Path + "\\" + Filename);
                if (!Dir2.exists()) {
                    Dir2.mkdir();
                }

                Path Destination = Paths.get(BasePath + Path + "\\" + Filename + "\\" + ListOfFiles[i].getName());
                // To move file from one directory to another and if same name file exist in directory than it will replace.
                Files.move(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
            } else if (Structure == "Multiple") {
                String CompleteFilename = ListOfFiles[i].getName();
                String FileWithoutExtension = FilenameUtils.removeExtension(CompleteFilename);
                String[] SplitFilename = FileWithoutExtension.split(SiteLevel + "-");
                String OS_Browser = SplitFilename[1];

                File Dir2 = new File(BasePath + Path + "\\" + OS_Browser);
                if (!Dir2.exists()) {
                    Dir2.mkdir();
                }

                File Dir3 = new File(BasePath + Path + "\\" + OS_Browser + "\\" + Filename);
                if (!Dir3.exists()) {
                    Dir3.mkdir();
                }

                Path Destination = Paths.get(BasePath + Path + "\\" + OS_Browser + "\\" + Filename + "\\" + ListOfFiles[i].getName());
                // To move file from one directory to another and if same name file exist in directory than it will replace.
                Files.move(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    // To logout from the BrowserStack site.
    public void LogoutBrowserStack() throws IOException {
        driver.findElement(By.linkText("Account")).click();
        driver.findElement(By.linkText("Sign out")).click();
    }

    // To merge two folder into new folder, both folder should have same number of folders with same name.
    public void MergeFolders(String SiteLevel1, String SiteLevel2, String ScreenshotThrough, String Path, String Structure) throws IOException {
        // Create folder for both site level
        File ScreenshotComparisionDirectory = new File(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough);
        ScreenshotComparisionDirectory.mkdir();

        File SiteLevel1Folder = new File(BasePath + Path + SiteLevel1 + ScreenshotThrough);
        File SiteLevel2Folder = new File(BasePath + Path + SiteLevel2 + ScreenshotThrough);

        if (Structure == "Single") {
            // List folders of Sitelevel1 folder
            File[] SiteLevel1FolderFolders = SiteLevel1Folder.listFiles();
            // List folders of Sitelevel2 folder
            File[] SiteLevel2FolderFolders = SiteLevel2Folder.listFiles();

            for (int i = 0 ; i < SiteLevel1FolderFolders.length ; i++){
                // Create folder in folder of both site level
                File ScreenshotDirectory = new File(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel1FolderFolders[i].getName());
                ScreenshotDirectory.mkdir();

                // List files of folder in Sitelevel1 folder
                File[] SiteLevel1FolderFolderFiles = SiteLevel1FolderFolders[i].listFiles();
                // List files of folder in Sitelevel2 folder
                File[] SiteLevel2FolderFolderFiles = SiteLevel2FolderFolders[i].listFiles();

                for (int j = 0 ; j < SiteLevel1FolderFolderFiles.length ; j++){
                    // To construct the path of file.
                    Path Original = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "\\" + SiteLevel1FolderFolders[i].getName() + "\\" + SiteLevel1FolderFolderFiles[j].getName());
                    Path Destination = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel1FolderFolders[i].getName() + "\\" + SiteLevel1FolderFolderFiles[j].getName());
                    // To move file from one directory to another and if same name file exist in directory than it will replace.
                    Files.copy(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
                }
                for (int k = 0 ; k < SiteLevel2FolderFolderFiles.length ; k++){
                    // To construct the path of file.
                    Path Original = Paths.get(BasePath + Path + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel2FolderFolders[i].getName() + "\\" + SiteLevel2FolderFolderFiles[k].getName());
                    Path Destination = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel2FolderFolders[i].getName() + "\\" + SiteLevel2FolderFolderFiles[k].getName());
                    // To move file from one directory to another and if same name file exist in directory than it will replace.
                    Files.copy(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        else if (Structure == "Multiple"){
            // List browsers of Sitelevel1 folder
            File[] SiteLevel1Browsers = SiteLevel1Folder.listFiles();
            // List browsers of Sitelevel2 folder
            File[] SiteLevel2Browsers = SiteLevel2Folder.listFiles();

            for (int i = 0 ; i < SiteLevel1Browsers.length ; i++) {
                // Create browser folder in folder of both site level
                File BrowserDirectory = new File(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel1Browsers[i].getName());
                BrowserDirectory.mkdir();

                // List folders of Sitelevel1 browser folder
                File[] SiteLevel1BrowserFolders = SiteLevel1Browsers[i].listFiles();
                File[] SiteLevel2BrowserFolders = SiteLevel2Browsers[i].listFiles();

                for (int j = 0 ; j < SiteLevel1BrowserFolders.length ; j++) {
                    // Create folder in browser folder of both site level
                    File ScreenshotDirectory = new File(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel1Browsers[i].getName() + "\\" + SiteLevel1BrowserFolders[j].getName());
                    ScreenshotDirectory.mkdir();

                    // List files of folder in Sitelevel1 folder
                    File[] SiteLevel1BrowserFolderFiles = SiteLevel1BrowserFolders[j].listFiles();
                    // List files of folder in Sitelevel2 folder
                    File[] SiteLevel2BrowserFolderFiles = SiteLevel2BrowserFolders[j].listFiles();

                    for (int k = 0 ; k < SiteLevel1BrowserFolderFiles.length ; k++){
                        // To construct the path of file.
                        Path Original = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "\\" + SiteLevel1Browsers[i].getName() + "\\" + SiteLevel1BrowserFolders[j].getName() + "\\" + SiteLevel1BrowserFolderFiles[k].getName());
                        Path Destination = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel1Browsers[i].getName() + "\\" + SiteLevel1BrowserFolders[j].getName() + "\\" + SiteLevel1BrowserFolderFiles[k].getName());
                        // To move file from one directory to another and if same name file exist in directory than it will replace.
                        Files.copy(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                    for (int l = 0 ; l < SiteLevel2BrowserFolderFiles.length ; l++){
                        // To construct the path of file.
                        Path Original = Paths.get(BasePath + Path + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel2Browsers[i].getName() + "\\" + SiteLevel2BrowserFolders[j].getName() + "\\" + SiteLevel2BrowserFolderFiles[l].getName());
                        Path Destination = Paths.get(BasePath + Path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + SiteLevel2Browsers[i].getName() + "\\" + SiteLevel2BrowserFolders[j].getName() + "\\" + SiteLevel2BrowserFolderFiles[l].getName());
                        // To move file from one directory to another and if same name file exist in directory than it will replace.
                        Files.copy(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }
    }


    /**
     * In gallery, images are display in group of two-two according to width in image name. If four or more images have equal width
     * in their name than they will not display properly. So, it required to change the width in image name.
     */
    public void ChangeWidth(String SiteLevel1, String SiteLevel2, String ScreenshotThrough, String path) throws IOException {
        File Folder = new File(BasePath + path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough);
        File[] FolderFolders = Folder.listFiles();

        for (int i = 0 ; i < FolderFolders.length ; i++) {
            File FolderFolder = new File(BasePath + path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + FolderFolders[i].getName());
            File[] FolderFolderFiles = FolderFolder.listFiles();

            // Reduce folder length because unknown file "thumbs.db" created.
            int TotalFiles = FolderFolderFiles.length - 1;

            // Store unique width
            TreeSet<String> UniqueWidth = new TreeSet<String>();
            for (int j = 0 ; j < TotalFiles; j++) {
                UniqueWidth.add(FolderFolderFiles[j].getName().substring(0, FolderFolderFiles[j].getName().indexOf("_")));
            }

            // Iterate unique width one by one
            List<String> RepeatedWidth = new ArrayList<String>();
            Iterator<String> IterateWidth = UniqueWidth.iterator();
            while (IterateWidth.hasNext()) {
                int Count = 0;
                String Width = IterateWidth.next();
                for (int k = 0 ; k < TotalFiles ; k++) {
                    if (FolderFolderFiles[k].getName().contains(Width)) {
                        Count++;
                    }
                }
                // Store repeated width
                if (Count > 2) {
                    RepeatedWidth.add(Width);
                }
            }

            // Iterate repeated width one by one
            TreeSet<String> UniqueBrowserOfUniqueWidth = new TreeSet<String>();
            for (int k = 0; k < RepeatedWidth.size(); k++) {
                // Store unique browser
                for (int l = 0 ; l < TotalFiles ; l++) {
                    if (FolderFolderFiles[l].getName().contains(RepeatedWidth.get(k))) {
                        String S1 = FolderFolderFiles[l].getName().substring(StringUtils.ordinalIndexOf(FolderFolderFiles[l].getName(), "_", 2) + 1);
                        String S2 = FilenameUtils.removeExtension(S1);
                        String RemoveSiteLevel = new String();
                        if (S2.contains(SiteLevel1)) {
                            RemoveSiteLevel = S2.replace(SiteLevel1, "");
                        } else if (S2.contains(SiteLevel2)) {
                            RemoveSiteLevel = S2.replace(SiteLevel2, "");
                        }
                        UniqueBrowserOfUniqueWidth.add(RemoveSiteLevel);
                    }
                }
                // Iterate unique browser one by one
                int IncreaseWidth = 0;
                Iterator<String> IterateBrowser = UniqueBrowserOfUniqueWidth.iterator();
                while (IterateBrowser.hasNext()) {
                    String Browser = IterateBrowser.next();
                    for (int m = 0 ; m < TotalFiles ; m++) {
                        if (FolderFolderFiles[m].getName().contains(Browser)) {
                            int NewWidth = Integer.parseInt(RepeatedWidth.get(k)) + IncreaseWidth;
                            String RenameFile = FolderFolderFiles[m].getName().replace(RepeatedWidth.get(k), String.valueOf(NewWidth));
                            // Rename filename with new width
                            FolderFolderFiles[m].renameTo(new File(BasePath + path + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "\\" + FolderFolders[i].getName() + "\\" + RenameFile));
                        }
                    }
                    IncreaseWidth++;
                }
                UniqueBrowserOfUniqueWidth.clear();
            }
        }
    }

    /**
     * After screenshot comparision two file will generate but their name not correct to generate gallery. So, it required to
     * change both filename.
     */
    public void RenameFilesAfterScreenshotComparision(String Path) throws IOException {
        // File object representing the directory on the disk.
        File Folder = new File(BasePath + Path);
        // Returns the array of files of particular extensions.
        File[] List_Folders = Folder.listFiles();

        for (int i = 0 ; i < List_Folders.length ; i++) {
            File Files = new File(BasePath + Path + "\\" + List_Folders[i].getName());
            File[] List_Files = Files.listFiles();

            for (int j = 0 ; j < List_Files.length ; j++) {
                File Old_Name = new File(BasePath + Path + "\\" + List_Folders[i].getName() + "\\" + List_Files[j].getName());
                String FileNameWithOutExt = FilenameUtils.removeExtension(Old_Name.getName());

                if(FileNameWithOutExt.contains("diff")) {
                    // AboutUs-AboutHclTechnologies_8July-win7-firefox-diff.png
                    String S1 = List_Files[j].getName().substring(StringUtils.ordinalIndexOf(List_Files[j].getName(), "_", 2) + 1);
                    // 8July-win7-firefox-diff.png
                    String S2 = FilenameUtils.removeExtension(S1);
                    // 8July-win7-firefox-diff
                    String FileNameWithOutSpace = FileNameWithOutExt.replace(" ", "").replace("&", "and").replace(S2, "diff");
                    Old_Name.renameTo(new File(BasePath + Path + "\\" + List_Folders[i].getName() + "\\" + FileNameWithOutSpace + ".png"));
                }
                if(FileNameWithOutExt.contains("data")) {
                    String S1 = List_Files[j].getName().substring(StringUtils.ordinalIndexOf(List_Files[j].getName(), "_", 2) + 1);
                    String S2 = FilenameUtils.removeExtension(S1);
                    String FileNameWithOutSpace = FileNameWithOutExt.replace(" ", "").replace("&", "and").replace(S2, "data");
                    Old_Name.renameTo(new File(BasePath + Path + "\\" + List_Folders[i].getName() + "\\" + FileNameWithOutSpace + ".txt"));
                }
            }
        }
    }

    // To login the site.
    public void Login(String UserName, String Password, String BaseUrl) throws IOException {
        driver.get(BaseUrl);
        driver.findElement(By.linkText("LOG IN")).click();
        driver.findElement(By.id("edit-name")).clear();
        driver.findElement(By.id("edit-name")).sendKeys(UserName);
        driver.findElement(By.id("edit-pass")).clear();
        driver.findElement(By.id("edit-pass")).sendKeys(Password);
        driver.findElement(By.xpath("//input[@class='form-submit']")).click();
    }
}