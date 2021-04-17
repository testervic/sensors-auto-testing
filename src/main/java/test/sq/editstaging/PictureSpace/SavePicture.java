package test.sq.editstaging.PictureSpace;

import browser.TestBaseSetup;
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

import java.io.IOException;

public class SavePicture extends BaseTestMethod {

    @Test(priority = 1)
    public void sqSavePicture() throws InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqSavePicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/SavePicture.yaml");
        Reporter.log("<b><h2>CASE："+SqSavePicture.CaseName()+"</h2></b>");
        SqSavePicture.operate();
        Assert.assertTrue(SqSavePicture.checkpoint(this.browserType), "检查点不通过");
    }
}
