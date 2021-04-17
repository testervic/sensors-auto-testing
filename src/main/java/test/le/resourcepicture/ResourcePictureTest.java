package test.le.resourcepicture;

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
import util.OperateElement;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class ResourcePictureTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";

    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }


    //资源图生成
    @Test(priority = 2, groups = {"high"})
    public void resourcePictureGenerate() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage resourcePictureGenerateCheck = new PublicPage(this.driver, "/le/ResourcePicture/ResourcePictureGenerateCheck.yaml");
        Reporter.log("<b><h2>CASE：" + resourcePictureGenerateCheck.CaseName() + "</h2></b>");
        resourcePictureGenerateCheck.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean status = isElement.waitForElement(By.xpath("(//span[text()='优化完毕'])[1]"));
        Reporter.log("<b><h2>CASE：是否复核完毕：" + status + "</h2></b>");
        //判断是否有图
        if (status) {
//            driver.findElement(By.xpath("(//input[@class='custom-checkbox'])[2]")).click();//点击勾选
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//a[@title='生成资源图'])[1]")).click();//点击生成资源图
            Thread.sleep(2000);
            Boolean windown = isElement.waitForElement(By.xpath("//button[text()='OK']"));
            //判断是否还在生成中
            if (windown) {
                Reporter.log("<b><h2>资源图还在生成中...</h2></b>");
            } else {
                PublicPage resourcePictureGenerate = new PublicPage(this.driver, "/le/ResourcePicture/ResourcePictureGenerate.yaml");
                Reporter.log("<b><h2>CASE：" + resourcePictureGenerate.CaseName() + "</h2></b>");
                resourcePictureGenerate.operate();
                Assert.assertTrue(resourcePictureGenerate.checkpoint(this.browserType), "检查点不通过");
            }
        } else {
            Reporter.log("<b><h2>CASE：正在复核中，跳过资源图生成CASE</h2></b>");
        }
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
