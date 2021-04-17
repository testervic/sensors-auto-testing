package test.sq.link;

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

public class CopyLink extends BaseTestClass {

    @Test(priority = 2,groups = "middle")
    public void lookLink() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage lookLink = new PublicPage(this.driver, "/sq/ProductManagement/LookLink.yaml");
        Reporter.log("<b><h2>CASE："+lookLink.CaseName()+"</h2></b>");
        lookLink.operate();
        Assert.assertTrue(lookLink.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 3)
    public void copyLink() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage copyLink = new PublicPage(this.driver, "/sq/LinkManagement/CopyLink.yaml");
        Reporter.log("<b><h2>CASE："+copyLink.CaseName()+"</h2></b>");
        copyLink.operate();
        Assert.assertTrue(copyLink.checkpoint(this.browserType), "检查点不通过");
    }
}
