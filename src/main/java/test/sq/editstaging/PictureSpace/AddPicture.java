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

public class AddPicture extends BaseTestMethod {

    @Test(priority = 1)
    public void sqAddPicture() throws InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqAddPicture1 = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/AddPicture1.yaml");
        Reporter.log("<b><h2>CASE："+SqAddPicture1.CaseName()+"</h2></b>");
        SqAddPicture1.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[17]/div/div/div/div/div[1]/div[1]/div[2]/input")).sendKeys(screenPath+"/testfile/AutoTesting.png");
        Assert.assertTrue(SqAddPicture1.checkpoint(this.browserType), "检查点不通过");
        PublicPage SqAddPicture2 = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/AddPicture2.yaml");
        Reporter.log("<b><h2>CASE："+SqAddPicture2.CaseName()+"</h2></b>");
        SqAddPicture2.operate();
        Assert.assertTrue(SqAddPicture2.checkpoint(this.browserType), "检查点不通过");
    }
}
