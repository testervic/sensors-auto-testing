package test.sq.homepage;

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

public class CheckHomePage extends BaseTestMethod {

    @Test(priority = 1)
    public void checkHomePage() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkHomePage = new PublicPage(this.driver, "/sq/HomePage/HomePageCheck.yaml");
        Reporter.log("<b><h2>CASE："+checkHomePage.CaseName()+"</h2></b>");
        checkHomePage.operate();
        Assert.assertTrue(checkHomePage.checkpoint(this.browserType), "检查点不通过");
    }
}
