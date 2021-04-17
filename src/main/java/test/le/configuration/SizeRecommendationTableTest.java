package test.le.configuration;

import browser.TestBaseSetup;
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
import util.WebBrower;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SizeRecommendationTableTest {
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式
    String dateNow = df.format(new Date());
    private WebDriver driver;
    WebBrower webBrower;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    //配置-预填尺码数据-新建
    @Test(priority = 1, groups = {"addSizeRecommendationTable"})
    public void AddSizeRecommendationTable() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addSizeRecommendationTableCheck = new PublicPage(this.driver, "/le/Configuration/SizeRecommendationTable/AddSizeRecommendationTable.yaml");
        Reporter.log("<b><h2>CASE：" + addSizeRecommendationTableCheck.CaseName() + "</h2></b>");
        addSizeRecommendationTableCheck.operate();
        Assert.assertTrue(addSizeRecommendationTableCheck.checkpoint(this.browserType), "检查点不通过");
    }


    //配置-预填尺码数据-修改、删除
    @Test(priority = 2, groups = {"modifySizeRecommendationTable"})
    public void modifySizeRecommendationTable() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifySizeRecommendationTable = new PublicPage(this.driver, "/le/Configuration/SizeRecommendationTable/ModifySizeRecommendationTable.yaml");
        Reporter.log("<b><h2>CASE：" + modifySizeRecommendationTable.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifySizeRecommendationTable.operate();
        Assert.assertTrue(modifySizeRecommendationTable.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
