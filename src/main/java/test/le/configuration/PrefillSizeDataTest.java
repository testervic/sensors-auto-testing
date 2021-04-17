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

public class PrefillSizeDataTest {
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
    @Test(priority = 1, groups = {"addPrefillSizeData"})
    public void AddPrefillSizeData() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addPrefillSizeDataCheck = new PublicPage(this.driver, "/le/Configuration/PrefillSizeData/AddPrefillSizeData.yaml");
        Reporter.log("<b><h2>CASE：" + addPrefillSizeDataCheck.CaseName() + "</h2></b>");
        addPrefillSizeDataCheck.operate();
        Assert.assertTrue(addPrefillSizeDataCheck.checkpoint(this.browserType), "检查点不通过");
    }


    //配置-预填尺码数据-修改、删除
    @Test(priority = 2, groups = {"modifyPrefillSizeData"})
    public void modifyPrefillSizeData() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifyPrefillSizeData = new PublicPage(this.driver, "/le/Configuration/PrefillSizeData/ModifyPrefillSizeData.yaml");
        Reporter.log("<b><h2>CASE：" + modifyPrefillSizeData.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifyPrefillSizeData.operate();
        Assert.assertTrue(modifyPrefillSizeData.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
