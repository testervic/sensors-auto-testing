package test.le.resultexport;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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
import util.OperateElement;
import util.Retry;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductFieldExportTest {
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式
    String dateNow = df.format(new Date());
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @Test(priority = 1)
    //成果导出-产品字段导出-基础字段导出
    public void productBaseFieldExport() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage productBaseFieldExport = new PublicPage(this.driver, "/le/ResultExport/ProductFieldExport/ProductBaseFieldExport.yaml");
        Reporter.log("<b><h2>CASE：" + productBaseFieldExport.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        productBaseFieldExport.operate();
        Assert.assertTrue(productBaseFieldExport.checkpoint(this.browserType), "检查点不通过");
    }

    @Test(priority = 1)
    //成果导出-产品字段导出-扩展字段导出
    public void producteEtensionFieldExport() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage producteEtensionFieldExport = new PublicPage(this.driver, "/le/ResultExport/ProductFieldExport/ProducteEtensionFieldExport.yaml");
        Reporter.log("<b><h2>CASE：" + producteEtensionFieldExport.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        producteEtensionFieldExport.operate();
        Assert.assertTrue(producteEtensionFieldExport.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
