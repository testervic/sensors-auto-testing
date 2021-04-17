package test.le.smartpuzzle;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultsFineTuningTest {
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
    //智能拼图-成功微调
    public void resultsFineTuning() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage resultsFineTuning = new PublicPage(this.driver, "/le/SmartPuzzle/TemplateDesign/ResultsFineTuning.yaml");
        Reporter.log("<b><h2>CASE：" + resultsFineTuning.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        resultsFineTuning.operate();
        Assert.assertTrue(resultsFineTuning.checkpoint(this.browserType), "检查点不通过");
    }


    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
