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

public class RulesConfigurationTest1 {
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

    //配置-规则配置-价格配置
    @Test(priority = 1)
    public void PriceConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage priceConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/PriceConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + priceConfigurationCheck.CaseName() + "</h2></b>");
        priceConfigurationCheck.operate();
        Assert.assertTrue(priceConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-产品标题生成配置
    @Test(priority = 2)
    public void ProductTitleGenerationConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage productTitleGenerationConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/ProductTitleGenerationConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + productTitleGenerationConfigurationCheck.CaseName() + "</h2></b>");
        productTitleGenerationConfigurationCheck.operate();
        Assert.assertTrue(productTitleGenerationConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-产品字段默认值配置
    @Test(priority = 3)
    public void ProductFieldDefaultValueConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage productFieldDefaultValueConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/ProductFieldDefaultValueConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + productFieldDefaultValueConfigurationCheck.CaseName() + "</h2></b>");
        productFieldDefaultValueConfigurationCheck.operate();
        Assert.assertTrue(productFieldDefaultValueConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //配置-规则配置-尺码转义配置
    @Test(priority = 4)
    public void SizeTranslateConfiguration() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage sizeTranslateConfigurationCheck = new PublicPage(this.driver, "/le/Configuration/RulesConfiguration/SizeTranslateConfiguration.yaml");
        Reporter.log("<b><h2>CASE：" + sizeTranslateConfigurationCheck.CaseName() + "</h2></b>");
        sizeTranslateConfigurationCheck.operate();
        Assert.assertTrue(sizeTranslateConfigurationCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
