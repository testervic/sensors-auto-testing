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

public class WashingIconTest {
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

    //配置-新建图标
    @Test(priority = 1, groups = {"addWashingIcon"})
    public void addWashingIcon() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addWashingIcon = new PublicPage(this.driver, "/le/Configuration/WashingIcon/AddWashingIcon.yaml");
        Reporter.log("<b><h2>CASE：" + addWashingIcon.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        addWashingIcon.operate();
        Assert.assertTrue(addWashingIcon.checkpoint(this.browserType), "检查点不通过");
    }

    //    配置-修改图标
    @Test(priority = 2, groups = {"modifyWashingIcon"})
    public void modifyWashingIcon() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifyWashingIcon = new PublicPage(this.driver, "/le/Configuration/WashingIcon/ModifyWashingIcon.yaml");
        Reporter.log("<b><h2>CASE：" + modifyWashingIcon.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifyWashingIcon.operate();
        Assert.assertTrue(modifyWashingIcon.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
