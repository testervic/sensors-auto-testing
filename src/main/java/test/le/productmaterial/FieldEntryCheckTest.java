package test.le.productmaterial;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import util.Retry;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FieldEntryCheckTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    //    字段录入-检查销售、商品属性-works
    @Test(priority = 1)
    public void fieldEntrySalesCheck() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLoginWorks();
        PublicPage FieldEntrySaleCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySaleCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySaleCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntrySaleCheck.operate();
        Assert.assertTrue(FieldEntrySaleCheck.checkpoint(this.browserType), "检查点不通过");
        //FieldEntryProductCheck
        PublicPage FieldEntryProductCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntryProductCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntryProductCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntryProductCheck.operate();
        Assert.assertTrue(FieldEntryProductCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //字段录入-检查销售、商品属性-Testhdw
    @Test(priority = 1)
    public void fieldEntrySalesCheckTesthdw() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLoginTesthdw();
        PublicPage FieldEntrySaleCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySaleCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySaleCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntrySaleCheck.operate();
        Assert.assertTrue(FieldEntrySaleCheck.checkpoint(this.browserType), "检查点不通过");
        //FieldEntryProductCheck
        PublicPage FieldEntryProductCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntryProductCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntryProductCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntryProductCheck.operate();
        Assert.assertTrue(FieldEntryProductCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //字段录入-检查销售、商品属性-Hdwtest
    @Test(priority = 1)
    public void fieldEntryCheckHdwtest() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLoginHdwtest();
        PublicPage FieldEntrySaleCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySaleCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySaleCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntrySaleCheck.operate();
        Assert.assertTrue(FieldEntrySaleCheck.checkpoint(this.browserType), "检查点不通过");
        //FieldEntryProductCheck
        PublicPage FieldEntryProductCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntryProductCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntryProductCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntryProductCheck.operate();
        Assert.assertTrue(FieldEntryProductCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //字段录入-检查销售、商品属性-Tryout1
    @Test(priority = 1)
    public void fieldEntrySalesCheckTryout1() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogintTryout1();
        PublicPage FieldEntrySaleCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySaleCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySaleCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntrySaleCheck.operate();
        Assert.assertTrue(FieldEntrySaleCheck.checkpoint(this.browserType), "检查点不通过");
        //FieldEntryProductCheck
        PublicPage FieldEntryProductCheck = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntryProductCheck.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntryProductCheck.CaseName() + "-" + df.format(new Date()) + "</h2></b>");
        FieldEntryProductCheck.operate();
        Assert.assertTrue(FieldEntryProductCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
