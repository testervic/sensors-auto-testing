package test.le.productmaterial;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import util.OperateElement;
import util.Retry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageImageEmptyTest {
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

    @Test(priority = 4, groups = {"middle"})
    //字段录入-检查指定商品是否存在图片
    public void imageEmpty() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        //ImageEmpty
        PublicPage publicPage = new PublicPage(this.driver, "/le/ProductMaterial/ImageUpload/ImageEmpty.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + " " + df.format(new Date()) + "</h2></b>");

        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
        //ImageEmpty1
        PublicPage ImageEmpty1 = new PublicPage(this.driver, "/le/ProductMaterial/ImageUpload/ImageEmpty1.yaml");
        OperateElement isElement = new OperateElement(this.driver);
        Boolean status = isElement.waitForElement(By.xpath("//span[text()='优化完毕']"));
        //判断是否需要情况图片,有就清空
        if (status) {
            Reporter.log("<b><h2>CASE：" + ImageEmpty1.CaseName() + " " + df.format(new Date()) + "</h2></b>");
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/table/tbody/tr[2]/td[6]/a[1]")).click();//点击图片
            Thread.sleep(2000);
            Boolean isPop = isElement.waitForElement(By.xpath("//button[text()='OK']"));
            if (!isPop) {
                ImageEmpty1.operate();
                Assert.assertTrue(ImageEmpty1.checkpoint(this.browserType), "检查点不通过");
            } else {
                driver.findElement(By.xpath("//button[text()='OK']")).click();
                ImageEmpty1.operate();
                Assert.assertTrue(ImageEmpty1.checkpoint(this.browserType), "检查点不通过");
            }
        } else {
            Reporter.log("<b><h2>还在复核中......</h2></b>");
        }
    }


    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
