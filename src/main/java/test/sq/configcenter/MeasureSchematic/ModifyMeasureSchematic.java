package test.sq.configcenter.MeasureSchematic;

import com.esotericsoftware.yamlbeans.YamlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ModifyMeasureSchematic extends BaseTestClass {

    @Test(priority = 2,groups = "middle")
    public void modifyMeasureSchematic() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage modifyMeasureSchematic = new PublicPage(this.driver, "/sq/ConfigCenter/MeasureSchematic/ModifyMeasureSchematic.yaml");
        Reporter.log("<b><h2>CASE："+modifyMeasureSchematic.CaseName()+"</h2></b>");
        modifyMeasureSchematic.operate();
        Assert.assertTrue(modifyMeasureSchematic.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 3)
    public void deleteMeasureSchematic() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        PublicPage deleteMeasureSchematic = new PublicPage(this.driver, "/sq/ConfigCenter/MeasureSchematic/DeleteMeasureSchematic.yaml");
        Reporter.log("<b><h2>CASE："+deleteMeasureSchematic.CaseName()+"</h2></b>");
        deleteMeasureSchematic.operate();
        Assert.assertTrue(deleteMeasureSchematic.checkpoint(this.browserType), "检查点不通过");
    }

}
