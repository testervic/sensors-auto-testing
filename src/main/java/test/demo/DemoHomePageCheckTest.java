package test.demo;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import util.Retry;

import java.io.FileNotFoundException;

public class DemoHomePageCheckTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";
    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @Test(priority = 1)
    //字段录入-检查销售属性
    public void demoHomePageCheck() throws YamlException, FileNotFoundException, InterruptedException {
        DemoLoginTest demoLoginTest = new DemoLoginTest(this.driver);
        demoLoginTest.demoLogin();
        PublicPage publicPage = new PublicPage(this.driver, "/demo/DemoHomePageCheck.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }


    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
