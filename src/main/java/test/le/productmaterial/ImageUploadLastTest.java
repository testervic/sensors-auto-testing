package test.le.productmaterial;

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
import util.Retry;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUploadLastTest {
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


    @Test(priority = 5, groups = {"low"})
    //产品素材-图片上传-上传本地图片包
    public void imageUpload() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage ImageUpload = new PublicPage(this.driver, "/le/ProductMaterial/ImageUpload/ImageUpload.yaml");
        Reporter.log("<b><h2>CASE：" + ImageUpload.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        ImageUpload.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        SimpleDateFormat hours = new SimpleDateFormat("HH");//设置日期格式
        String hoursNow = hours.format(new Date());
        Thread.sleep(3000);

        //8点才执行
//        if(hoursNow.equals("08")){
//            try {
//                //处理弹窗
//                driver.findElement(By.xpath("//div[text()='继续上传']")).click();
//            }
//            catch (Exception e){
//                ImageUpload.TakesScreenshot("error");
//                Reporter.log("<b></br>ERROR：检查点失败<b></br>"+e);
//            }
//        }

        //上传图片包，避免频繁修改路径
        driver.findElement(By.xpath("//input[contains(@id, 'html5')]")).sendKeys(screenPath + "/testfile/AutoUITesting.zip");
        Assert.assertTrue(ImageUpload.checkpoint(this.browserType), "检查点不通过");
        //ImageUpload1
        PublicPage ImageUpload1 = new PublicPage(this.driver, "/le/ProductMaterial/ImageUpload/ImageUpload1.yaml");
        Reporter.log("<b><h2>CASE：" + ImageUpload1.CaseName() + " " + df.format(new Date()) + "</h2></b>");
        ImageUpload1.operate();
        Assert.assertTrue(ImageUpload1.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
