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

public class StartWaterMark extends BaseTestClass {

    @Test(priority = 3,groups = "middle")
    public void startWaterMarkRule() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage startWaterMarkRule = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/StartWaterMarkRule.yaml");
        Reporter.log("<b><h2>CASE："+startWaterMarkRule.CaseName()+"</h2></b>");
        startWaterMarkRule.operate();
        Assert.assertTrue(startWaterMarkRule.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 4)
    public void startWaterMarkGroup() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        PublicPage startWaterMarkGroup = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/StartWaterMarkGroup.yaml");
        Reporter.log("<b><h2>CASE："+startWaterMarkGroup.CaseName()+"</h2></b>");
        startWaterMarkGroup.operate();
        Assert.assertTrue(startWaterMarkGroup.checkpoint(this.browserType), "检查点不通过");
    }

}
