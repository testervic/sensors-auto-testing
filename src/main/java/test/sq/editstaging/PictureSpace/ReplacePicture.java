package test.sq.editstaging.PictureSpace;

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
import test.sq.login.SqLoginTest;

import java.io.File;
import java.io.IOException;

public class ReplacePicture extends BaseTestMethod {
    @Test(priority = 1)
    public void sqReplacePicture() throws InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqReplacePicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/ReplacePicture.yaml");
        Reporter.log("<b><h2>CASE："+SqReplacePicture.CaseName()+"</h2></b>");
        SqReplacePicture.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        Thread.sleep(1000);
        driver.findElement(By.id("MODEL")).sendKeys(screenPath+"/testfile/AutoTesting.png");
        Assert.assertTrue(SqReplacePicture.checkpoint(this.browserType), "检查点不通过");
    }
}
