package test;

import browser.TestBaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import util.TestngListener;


@Listeners(TestngListener.class)
public class BaseTestMethod {
    protected WebDriver driver;
    protected TestBaseSetup testBaseSetup = new TestBaseSetup();
    protected String browserType = "";
    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})

    @BeforeMethod
    protected void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @AfterMethod
    protected void tearDown() {
        this.driver.quit();
    }
}
