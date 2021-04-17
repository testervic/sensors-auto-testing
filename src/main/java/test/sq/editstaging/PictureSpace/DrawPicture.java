package test.sq.editstaging.PictureSpace;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;

public class DrawPicture extends BaseTestMethod {
    @Test(priority = 1)
    public void sqDrawPicture() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqDrawPicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/DrawPicture.yaml");
        Reporter.log("<b><h2>CASE："+SqDrawPicture.CaseName()+"</h2></b>");
        SqDrawPicture.operate();
        Assert.assertTrue(SqDrawPicture.checkpoint(this.browserType), "检查点不通过");
    }
}
