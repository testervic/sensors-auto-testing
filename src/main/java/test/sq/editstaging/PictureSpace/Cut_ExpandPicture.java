package test.sq.editstaging.PictureSpace;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;

public class Cut_ExpandPicture extends BaseTestClass {

    @Test(priority = 1)
    public void sqCutPicture() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqCutPicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/CutPicture.yaml");
        Reporter.log("<b><h2>CASE："+SqCutPicture.CaseName()+"</h2></b>");
        SqCutPicture.operate();
        Assert.assertTrue(SqCutPicture.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 2)
    public void sqExpandPicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage SqExpandPicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/ExpandPicture.yaml");
        Reporter.log("<b><h2>CASE："+SqExpandPicture.CaseName()+"</h2></b>");
        SqExpandPicture.operate();
        Assert.assertTrue(SqExpandPicture.checkpoint(this.browserType), "检查点不通过");
    }
}
