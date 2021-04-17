package test.le.shortvideo;

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

public class ShortVideoTemplateTest {
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

    //配置-新建短视频模板
    @Test(priority = 1, groups = {"addShortVideoTemplate"})
    public void addShortVideoTemplate() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addShortVideoTemplate = new PublicPage(this.driver, "/le/ShortVideo/ShortVideoTemplate/AddShortVideoTemplate.yaml");
        Reporter.log("<b><h2>CASE：" + addShortVideoTemplate.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        addShortVideoTemplate.operate();
        Assert.assertTrue(addShortVideoTemplate.checkpoint(this.browserType), "检查点不通过");
    }

    //    配置-修改短视频模板
    @Test(priority = 2, groups = {"modifyShortVideoTemplate"})
    public void modifyShortVideoTemplate() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifyShortVideoTemplate = new PublicPage(this.driver, "/le/ShortVideo/ShortVideoTemplate/ModifyShortVideoTemplate.yaml");
        Reporter.log("<b><h2>CASE：" + modifyShortVideoTemplate.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifyShortVideoTemplate.operate();
        Assert.assertTrue(modifyShortVideoTemplate.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
