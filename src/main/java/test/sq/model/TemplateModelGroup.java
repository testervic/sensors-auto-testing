package test.sq.model;

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
import java.io.IOException;

public class TemplateModelGroup extends BaseTestMethod {
    @Test(priority = 1,groups = "addGroup")
    public void addGroup() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLogin = new SqLoginTest(driver);
        sqLogin.sqLoginSQTEST();
        PublicPage addGroup = new PublicPage(this.driver, "/sq/TemplateManagement/AddGroup.yaml");
        Reporter.log("<b><h2>CASE："+addGroup.CaseName()+"</h2></b>");
        addGroup.operate();
        Assert.assertTrue(addGroup.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 2,groups = "deleteGroup")
    public void deleteGroup() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLogin = new SqLoginTest(driver);
        sqLogin.sqLoginSQTEST();
        PublicPage deleteGroup = new PublicPage(this.driver, "/sq/TemplateManagement/DeleteGroup.yaml");
        Reporter.log("<b><h2>CASE："+deleteGroup.CaseName()+"</h2></b>");
        deleteGroup.operate();
        Assert.assertTrue(deleteGroup.checkpoint(this.browserType), "检查点不通过");
    }
}
