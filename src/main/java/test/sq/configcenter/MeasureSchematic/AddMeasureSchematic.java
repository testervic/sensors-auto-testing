package test.sq.configcenter.MeasureSchematic;

import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddMeasureSchematic extends BaseTestMethod {

    @Test(priority = 1,groups = "high")
    public void addMeasureSchematic() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage addMeasureSchematic = new PublicPage(this.driver, "/sq/ConfigCenter/MeasureSchematic/AddMeasureSchematic.yaml");
        Reporter.log("<b><h2>CASE："+addMeasureSchematic.CaseName()+"</h2></b>");
        addMeasureSchematic.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".ant-upload-select .ant-upload > input")).sendKeys(screenPath+"/testfile/AutoTesting.png");
        Assert.assertTrue(addMeasureSchematic.checkpoint(this.browserType), "检查点不通过");
        Thread.sleep(2000);
        PublicPage addMeasureSchematic1 = new PublicPage(this.driver, "/sq/ConfigCenter/MeasureSchematic/AddMeasureSchematic1.yaml");
        Reporter.log("<b><h2>CASE："+addMeasureSchematic1.CaseName()+"</h2></b>");
        addMeasureSchematic1.operate();
        Assert.assertTrue(addMeasureSchematic1.checkpoint(this.browserType), "检查点不通过");
    }

}
