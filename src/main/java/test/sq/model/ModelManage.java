package test.sq.model;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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
import java.io.FileNotFoundException;
import java.io.IOException;

public class ModelManage extends BaseTestMethod {
    @Test(priority = 1)
    public void modifyModel() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLogin = new SqLoginTest(driver);
        sqLogin.sqLoginSQTEST();
        PublicPage modifyModel = new PublicPage(this.driver, "/sq/TemplateManagement/ModifyModel.yaml");
        Reporter.log("<b><h2>CASE："+modifyModel.CaseName()+"</h2></b>");
        modifyModel.operate();
        Assert.assertTrue(modifyModel.checkpoint(this.browserType), "检查点不通过");
    }
}
