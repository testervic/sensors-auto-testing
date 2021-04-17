package test.le.resultexport;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import util.OperateElement;
import util.Retry;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class LoadAndSynchronizationTest {
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

    @Test(priority = 3, groups = {"middle"})
    //字段录入-检查销售属性
    public void loadAndSynchronization() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        //LoadAndSynchronization
        PublicPage publicPage = new PublicPage(this.driver, "/le/ResultExport/LoadAndSynchronization.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        publicPage.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean authorization = isElement.waitForElement(By.xpath("//span[text()='授权已过期']"));
        //判断店铺是否授权
        if (!authorization) {
            //LoadAndSynchronization1
            PublicPage LoadAndSynchronization1 = new PublicPage(this.driver, "/le/ResultExport/LoadAndSynchronization1.yaml");
            Reporter.log("<b><h2>CASE：" + LoadAndSynchronization1.CaseName() + " " + df.format(new Date()) + "</h2></b>");
            LoadAndSynchronization1.operate();
            Assert.assertTrue(LoadAndSynchronization1.checkpoint(this.browserType), "检查点不通过");
        } else {
            Reporter.log("<b><h2>CASE：店铺授权失效，跳过上货CASE " + df.format(new Date()) + "</h2></b>");
        }
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
