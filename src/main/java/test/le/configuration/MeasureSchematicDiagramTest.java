package test.le.configuration;

import browser.TestBaseSetup;
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
import util.WebBrower;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeasureSchematicDiagramTest {
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

    //配置-测量示意图
    @Test(priority = 1)
    public void MeasureSchematicDiagram() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage measureSchematicDiagram1Check = new PublicPage(this.driver, "/le/Configuration/MeasureSchematicDiagram/MeasureSchematicDiagram1.yaml");
        Reporter.log("<b><h2>CASE：" + measureSchematicDiagram1Check.CaseName() + "</h2></b>");
        measureSchematicDiagram1Check.operate();
        Assert.assertTrue(measureSchematicDiagram1Check.checkpoint(this.browserType), "检查点不通过");

        File imageDirectory = new File("res");
        String screenPath = imageDirectory.getCanonicalPath();
        Thread.sleep(3000);
        System.out.println("screenPath：" + screenPath);
        try {
            //上传图片文件，避免频繁修改路径
            driver.findElement(By.cssSelector(".divBasic1 > input:nth-child(3)")).sendKeys(screenPath + "/testfile/AutoTesting.png");
            Thread.sleep(1000);
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
        try {
            //点击保存
            driver.findElement(By.xpath("//span[text()='保存']")).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
        //MeasureSchematicDiagram2Check
        PublicPage measureSchematicDiagram2Check = new PublicPage(this.driver, "/le/Configuration/MeasureSchematicDiagram/MeasureSchematicDiagram2.yaml");
        Reporter.log("<b><h2>CASE：" + measureSchematicDiagram2Check.CaseName() + "</h2></b>");
        measureSchematicDiagram2Check.operate();
        Assert.assertTrue(measureSchematicDiagram2Check.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
