package test.sq.uploadstaging.Image;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;

public class ImageAddEdit {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";

    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @Test(priority = 1,groups = {"first"})
    public void imageAddEdit() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        //放入编辑工作台
        PublicPage imageAddEdit = new PublicPage(this.driver, "/sq/UploadStaging/Image/ImageAddEdit.yaml");
        Reporter.log("<b><h2>CASE："+imageAddEdit.CaseName()+"</h2></b>");
        imageAddEdit.operate();
        Assert.assertTrue(imageAddEdit.checkpoint(this.browserType), "检查点不通过");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
