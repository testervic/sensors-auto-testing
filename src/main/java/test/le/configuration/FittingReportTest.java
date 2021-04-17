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

public class FittingReportTest {
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

    //配置-自动化预设-新建
    @Test(priority = 1, groups = {"addFittingReport"})
    public void AddFittingReport() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addFittingReportCheck = new PublicPage(this.driver, "/le/Configuration/FittingReport/AddFittingReport.yaml");
        Reporter.log("<b><h2>CASE：" + addFittingReportCheck.CaseName() + "</h2></b>");
        addFittingReportCheck.operate();
        Assert.assertTrue(addFittingReportCheck.checkpoint(this.browserType), "检查点不通过");
    }


    //配置-修改模特
    @Test(priority = 2, groups = {"modifyFittingReport"})
    public void modifyFittingReport() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifyFittingReportCheck = new PublicPage(this.driver, "/le/Configuration/FittingReport/ModifyFittingReport.yaml");
        Reporter.log("<b><h2>CASE：" + modifyFittingReportCheck.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifyFittingReportCheck.operate();
        Assert.assertTrue(modifyFittingReportCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
