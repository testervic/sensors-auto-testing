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

public class EditFormsSystemExcel extends BaseTestMethod {

    @Test(priority = 2,groups = {"middle"})
    public void systemExcelForms() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage systemExcelForms = new PublicPage(this.driver, "/sq/EditStaging/SystemExcelForms.yaml");
        Reporter.log("<b><h2>CASE："+systemExcelForms.CaseName()+"</h2></b>");
        systemExcelForms.operate();
        Assert.assertTrue(systemExcelForms.checkpoint(this.browserType), "检查点不通过");
    }
}
