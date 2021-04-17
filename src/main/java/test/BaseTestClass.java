package test;

import browser.TestBaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.TestngListener;


@Listeners(TestngListener.class)
public class BaseTestClass {
    protected WebDriver driver;
    protected TestBaseSetup testBaseSetup = new TestBaseSetup();
    protected String browserType = "";
    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})

    @BeforeClass
    protected void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @AfterClass
    protected void tearDown() {
        this.driver.quit();
    }
}
