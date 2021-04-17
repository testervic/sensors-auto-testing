package test.sq.editstaging;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;
import util.Retry;

import java.io.FileNotFoundException;

public class EditFormsImage extends BaseTestMethod {

    @Test(priority = 2,groups = {"second"})
    public void inputForms() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage inputForms = new PublicPage(this.driver, "/sq/EditStaging/InputForms.yaml");
        Reporter.log("<b><h2>CASE："+inputForms.CaseName()+"</h2></b>");
        inputForms.operate();
        driver.navigate().refresh();
        Assert.assertTrue(inputForms.checkpoint(this.browserType), "检查点不通过");
        PublicPage saveForms = new PublicPage(this.driver, "/sq/EditStaging/SaveForms.yaml");
        Reporter.log("<b><h2>CASE："+saveForms.CaseName()+"</h2></b>");
        saveForms.operate();
        Assert.assertTrue(saveForms.checkpoint(this.browserType), "检查点不通过");
    }
}
