package test.le.productmaterial;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
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

public class FieldEntrySubmitStagingTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";
    SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");//设置日期格式
    String dateNow = df.format(new Date());

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @Test(priority = 1)
    //字段录入-检查销售属性
    public void fieldEntrySubmit() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage FieldEntrySubmit1 = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySubmit1.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySubmit1.CaseName() + "</h2></b>");
        FieldEntrySubmit1.operate();
        //单独处理货号
        Thread.sleep(2000);
        driver.findElement(By.id("code")).clear();  //清空货号
        driver.findElement(By.id("code")).sendKeys("AutoUI-" + dateNow); //输入货号
        Assert.assertTrue(FieldEntrySubmit1.checkpoint(this.browserType), "检查点不通过");
        //接着执行
        PublicPage FieldEntrySubmit2 = new PublicPage(this.driver, "/le/ProductMaterial/FieldEntry/FieldEntrySubmit2.yaml");
        Reporter.log("<b><h2>CASE：" + FieldEntrySubmit2.CaseName() + "</h2></b>");
        FieldEntrySubmit2.operate();
        Assert.assertTrue(FieldEntrySubmit2.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
