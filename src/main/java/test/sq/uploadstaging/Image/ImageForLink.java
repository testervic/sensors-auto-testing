package test.sq.uploadstaging.Image;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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
import test.sq.login.SqLoginTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageForLink {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";


    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }
    @Test(priority = 4,groups = {"fourth"})
    public void uploadImage() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        //上传图片包
        PublicPage uploadImage = new PublicPage(this.driver, "/sq/UploadStaging/Image/ImageUpload.yaml");
        Reporter.log("<b><h2>CASE："+uploadImage.CaseName()+"</h2></b>");
        uploadImage.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".ant-upload-select .ant-upload > input")).sendKeys(screenPath+"/testfile/AutoUITestPictureLink.zip");
        Thread.sleep(15000);
        driver.navigate().refresh();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[contains(@class, 'ops-code-input')]/input")).sendKeys("AutoUITestPictureLink");
        driver.findElement(By.xpath("//*[contains(@class, 'upload-search')]//*[contains(@class, 'ant-btn-primary')]")).click();
        Assert.assertTrue(uploadImage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 5)
    public void templateAddEdit() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage templateAddEdit = new PublicPage(this.driver, "/sq/UploadStaging/Image/TemplateAddEdit.yaml");
        Reporter.log("<b><h2>CASE："+templateAddEdit.CaseName()+"</h2></b>");
        templateAddEdit.operate();
        Assert.assertTrue(templateAddEdit.checkpoint(this.browserType), "检查点不通过");
    }
    @AfterMethod
    public void tearDown() {

        this.driver.quit();
    }
}
