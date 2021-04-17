package test.le.detailpage;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import test.le.productmaterial.ImageUploadTest;
import util.OperateElement;
import util.Retry;
import util.WebBrower;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutomaticTypesettingTest {
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

    @Test(priority = 1, groups = {"high"})
    //生成详情页
    public void automatictypesetting() throws IOException, InterruptedException {
        //登录
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage automatictypesetting = new PublicPage(this.driver, "/le/DetailPage/Automatictypesetting.yaml");
        PublicPage automatictypesettingCheck = new PublicPage(this.driver, "/le/DetailPage/AutomatictypesettingCheck.yaml");
        //检查商品有无图片
        automatictypesettingCheck.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean status = isElement.waitForElement(By.xpath("(//div[@class='product-img']//img)[1]"));
        //判断是否有图，有就生成详情页、没有就传图
        if (status) {
            Reporter.log("<b><h2>CASE：" + automatictypesetting.CaseName() + " " + df.format(new Date()) + "</h2></b>");
            automatictypesetting.operate();
            Assert.assertTrue(automatictypesetting.checkpoint(this.browserType), "检查点不通过");
        } else {
            //切换iframe
            this.driver.switchTo().defaultContent();
            Thread.sleep(2000);
            try {
                //上传图片,先退出
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/div/ul/li[8]"))).perform();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"top\"]/div/div[2]/div/ul/li[8]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button[text()='确认']")).click();
            } catch (Exception e) {
                Reporter.log("ERROR:" + e);
            }
            ImageUploadTest imageUploadTest = new ImageUploadTest(this.driver);
            Reporter.log("<b><h2>------------------没有图片先去上传图片-----------------</h2></b><br>");
            imageUploadTest.imageUpload();
        }
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
