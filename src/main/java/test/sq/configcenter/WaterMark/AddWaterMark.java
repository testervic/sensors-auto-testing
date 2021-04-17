package test.sq.configcenter.WaterMark;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddWaterMark extends BaseTestClass {

    @Test(priority = 1)
    public void addWaterMarkGroup() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage addWaterMarkGroup = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/AddWaterMarkGroup.yaml");
        Reporter.log("<b><h2>CASE："+addWaterMarkGroup.CaseName()+"</h2></b>");
        addWaterMarkGroup.operate();
        Assert.assertTrue(addWaterMarkGroup.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 2,groups = "high")
    public void addWaterMarkRule() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        PublicPage addWaterMarkRule = new PublicPage(this.driver, "/sq/ConfigCenter/WaterMark/AddWaterMarkRule.yaml");
        Reporter.log("<b><h2>CASE："+addWaterMarkRule.CaseName()+"</h2></b>");
        addWaterMarkRule.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".upload-file > input")).sendKeys(screenPath+"/testfile/AutoTesting.png");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@class, 'button-container')]//*[contains(@class, 'ant-btn-primary')]")).click();
        Assert.assertTrue(addWaterMarkRule.checkpoint(this.browserType), "检查点不通过");
    }

}
