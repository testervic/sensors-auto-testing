package test.sq.editstaging.PictureSpace;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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

import java.io.FileNotFoundException;

public class RenamePicture extends BaseTestMethod {
    @Test(priority = 1)
    public void sqRenamePicture() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage SqRenamePicture = new PublicPage(this.driver, "/sq/EditStaging/PictureSpace/RenamePicture.yaml");
        Reporter.log("<b><h2>CASE："+SqRenamePicture.CaseName()+"</h2></b>");
        SqRenamePicture.operate();
        Assert.assertTrue(SqRenamePicture.checkpoint(this.browserType), "检查点不通过");
    }
}
