package test.sq.configcenter.WaterMark;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DeleteWaterMark extends BaseTestClass {

    @Test(priority = 5,groups = "low")
    public void deleteWaterMarkRule() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteWaterMarkRule = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/DeleteWaterMarkRule.yaml");
        Reporter.log("<b><h2>CASE："+deleteWaterMarkRule.CaseName()+"</h2></b>");
        deleteWaterMarkRule.operate();
        Assert.assertTrue(deleteWaterMarkRule.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 6)
    public void deleteWaterMarkGroup() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        PublicPage deleteWaterMarkGroup = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/DeleteWaterMarkGroup.yaml");
        Reporter.log("<b><h2>CASE："+deleteWaterMarkGroup.CaseName()+"</h2></b>");
        deleteWaterMarkGroup.operate();
        Assert.assertTrue(deleteWaterMarkGroup.checkpoint(this.browserType), "检查点不通过");
    }

}
