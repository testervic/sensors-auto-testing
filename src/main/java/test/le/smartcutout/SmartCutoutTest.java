package test.le.smartcutout;

import browser.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.le.login.LoginTest;

import java.io.File;
import java.io.IOException;

public class SmartCutoutTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    //智能抠图
    @Test(priority = 1)
    public void smartCutout() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage smartCutout = new PublicPage(this.driver, "/le/SmartCutout/SmartCutout.yaml");
        Reporter.log("<b><h2>CASE：" + smartCutout.CaseName() + "</h2></b>");
        smartCutout.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        Thread.sleep(1000);
        try {
            //上传图片文件，避免频繁修改路径
            driver.findElement(By.xpath("/html/body/app-root/app-matting/div/div[2]/div[3]/nz-upload/div/div/input")).sendKeys(screenPath + "/testfile/SmartCutout-AutoUITesting.jpg");
            Thread.sleep(1000);
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
        //检查上传结果
        PublicPage ExcelImport1 = new PublicPage(this.driver, "/le/SmartCutout/SmartCutout1.yaml");
        Reporter.log("<b><h2>CASE：" + ExcelImport1.CaseName() + "</h2></b>");
        ExcelImport1.operate();
        Assert.assertTrue(ExcelImport1.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
