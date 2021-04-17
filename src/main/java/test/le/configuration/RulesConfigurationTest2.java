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

public class RulesConfigurationTest2 {
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

    //配置-规则配置-价格填写规则配置
    @Test(priority = 1)
    public void PriceFillRulesConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage priceFillRulesConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/PriceFillRulesConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + priceFillRulesConfigurationCheck.CaseName() + "</h2></b>");
        priceFillRulesConfigurationCheck.operate();
        Assert.assertTrue(priceFillRulesConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-折扣配置
    @Test(priority = 2)
    public void DiscountConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage discountConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/DiscountConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + discountConfigurationCheck.CaseName() + "</h2></b>");
        discountConfigurationCheck.operate();
        Assert.assertTrue(discountConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-号型配置
    @Test(priority = 3)
    public void ShapeConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage shapeConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/ShapeConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + shapeConfigurationCheck.CaseName() + "</h2></b>");
        shapeConfigurationCheck.operate();
        Assert.assertTrue(shapeConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-其他规则
    @Test(priority = 4)
    public void OtherRules() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage otherRulesCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/OtherRules.yaml");
        Reporter.log("<b><h2>CASE：" + otherRulesCheck.CaseName() + "</h2></b>");
        otherRulesCheck.operate();
        Assert.assertTrue(otherRulesCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
