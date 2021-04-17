package test.sq.link;

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

public class DeleteLink extends BaseTestMethod {

    @Test(priority = 4,groups = "low")
    public void deleteLink() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteLink = new PublicPage(this.driver, "/sq/LinkManagement/DeleteLink.yaml");
        Reporter.log("<b><h2>CASE："+deleteLink.CaseName()+"</h2></b>");
        deleteLink.operate();
        Assert.assertTrue(deleteLink.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 5)
    public void deepDeleteLink() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deepDeleteLink = new PublicPage(this.driver, "/sq/LinkManagement/DeepDeleteLink.yaml");
        Reporter.log("<b><h2>CASE："+deepDeleteLink.CaseName()+"</h2></b>");
        deepDeleteLink.operate();
        Assert.assertTrue(deepDeleteLink.checkpoint(this.browserType), "检查点不通过");
    }
}
