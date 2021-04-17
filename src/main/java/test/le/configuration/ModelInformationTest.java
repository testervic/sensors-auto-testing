package test.le.configuration;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import test.le.productmaterial.ImageUploadTest;
import util.OperateElement;
import util.Retry;
import util.WebBrower;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelInformationTest {
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

    //配置-新建模特
    @Test(priority = 1, groups = {"addModel"})
    public void addModel() throws IOException, InterruptedException {
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addModel = new PublicPage(this.driver, "/le/Configuration/ModelInformation/AddModel.yaml");
        Reporter.log("<b><h2>CASE：" + addModel.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        addModel.operate();
        try {
            Reporter.log("<br></br><b>STEP：开始上传模特头像</b>");
            Thread.sleep(1000);
            //上传模特头像照片
            driver.findElement(By.xpath("//*[@id=\"head\"]")).sendKeys(screenPath + "/testfile/AutoTesting.png");
            Thread.sleep(3000);
            //上传模特全身照
            driver.findElement(By.xpath("//*[@id=\"photo\"]")).sendKeys(screenPath + "/testfile/AutoTesting.png");
            Thread.sleep(3000);
            //点击保存
            driver.findElement(By.xpath("//span[text()='保存']")).click();
            Assert.assertTrue(addModel.checkpoint(this.browserType), "检查点不通过");
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
    }

    //配置-修改模特
    @Test(priority = 2, groups = {"modifyModel"})
    public void modifyModel() throws IOException, InterruptedException {
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage modifyModel = new PublicPage(this.driver, "/le/Configuration/ModelInformation/ModifyModel.yaml");
        Reporter.log("<b><h2>CASE：" + modifyModel.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        modifyModel.operate();
        Assert.assertTrue(modifyModel.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
