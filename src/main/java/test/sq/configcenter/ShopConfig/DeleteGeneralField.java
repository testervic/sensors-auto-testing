package test.sq.configcenter.ShopConfig;

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

public class DeleteGeneralField extends BaseTestMethod {

    @Test(priority = 3,groups = "low")
    public void deleteGeneralField() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteGeneralField = new PublicPage(this.driver, "/sq/ConfigCenter/ShopConfig/DeleteGeneralField.yaml");
        Reporter.log("<b><h2>CASE："+deleteGeneralField.CaseName()+"</h2></b>");
        deleteGeneralField.operate();
        Assert.assertTrue(deleteGeneralField.checkpoint(this.browserType), "检查点不通过");
    }

}
