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

public class SaveEmptyForms extends BaseTestMethod {

    @Test(priority = 2,groups = {"middle"})
    public void inputForms() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        //录入表单信息
        PublicPage inputForms = new PublicPage(this.driver, "/sq/EditStaging/EditEmptyLinkForms.yaml");
        Reporter.log("<b><h2>CASE："+inputForms.CaseName()+"</h2></b>");
        inputForms.operate();
        driver.navigate().refresh();
        Assert.assertTrue(inputForms.checkpoint(this.browserType), "检查点不通过");
        //表单自动同步并保存
        PublicPage saveForms = new PublicPage(this.driver, "/sq/EditStaging/SynchronizeForms.yaml");
        Reporter.log("<b><h2>CASE："+saveForms.CaseName()+"</h2></b>");
        saveForms.operate();
        Assert.assertTrue(saveForms.checkpoint(this.browserType), "检查点不通过");
    }
}
