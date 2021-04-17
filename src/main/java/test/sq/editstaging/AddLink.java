package test.sq.editstaging;

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

public class AddLink extends BaseTestMethod {

    @Test(priority = 1,groups = "high")
    public void addLink() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage addLink = new PublicPage(this.driver, "/sq/EditStaging/AddLink.yaml");
        Reporter.log("<b><h2>CASE："+addLink.CaseName()+"</h2></b>");
        addLink.operate();
        Assert.assertTrue(addLink.checkpoint(this.browserType), "检查点不通过");
    }
}
