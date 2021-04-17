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
import test.BaseTestMethod;
import test.le.login.LoginTest;

import java.io.File;
import java.io.IOException;

public class ImageUploadDrawRebornTest extends BaseTestMethod {

    //产品素材-综合列表-图片-单张上传
    @Test(priority = 1)
    public void imageSingleUpload() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageSingleUploadCheck = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageSingleUpload.yaml");
        Reporter.log("<b><h2>CASE：" + imageSingleUploadCheck.CaseName() + "</h2></b>");
        imageSingleUploadCheck.operate();
        Assert.assertTrue(imageSingleUploadCheck.checkpoint(this.browserType), "检查点不通过");

        File imageDirectory = new File("res");
        String screenPath = imageDirectory.getCanonicalPath();
        Thread.sleep(3000);
        try {
            //上传图片文件，避免频繁修改路径
            driver.findElement(By.xpath("/html/body/div[18]/div/div/div/div/div[1]/div[1]/div[2]/input")).sendKeys(screenPath + "/testfile/AutoTesting.png");
            Thread.sleep(1000);
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }

        //ImageSingleUpload2
        PublicPage imageSingleUpload2Check = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageSingleUpload2.yaml");
        Reporter.log("<b><h2>CASE：" + imageSingleUpload2Check.CaseName() + "</h2></b>");
        imageSingleUpload2Check.operate();
        Assert.assertTrue(imageSingleUpload2Check.checkpoint(this.browserType), "检查点不通过");
    }

    //产品素材-综合列表-图片-画图
    @Test(priority = 2)
    public void imageDraw() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageDrawCheck = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageDraw.yaml");
        Reporter.log("<b><h2>CASE：" + imageDrawCheck.CaseName() + "</h2></b>");
        imageDrawCheck.operate();
        Assert.assertTrue(imageDrawCheck.checkpoint(this.browserType), "检查点不通过");
    }

    //产品素材-综合列表-图片-重新生成资源图
    @Test(priority = 3)
    public void imageReborn() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageRebornCheck = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageReborn.yaml");
        Reporter.log("<b><h2>CASE：" + imageRebornCheck.CaseName() + "</h2></b>");
        imageRebornCheck.operate();
        Assert.assertTrue(imageRebornCheck.checkpoint(this.browserType), "检查点不通过");
    }
}
