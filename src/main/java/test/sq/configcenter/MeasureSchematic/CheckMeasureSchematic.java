package test.sq.configcenter.MeasureSchematic;

import com.esotericsoftware.yamlbeans.YamlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckMeasureSchematic extends BaseTestMethod {

    @Test(priority = 1)
    public void checkMeasureSchematic() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkMeasureSchematic = new PublicPage(this.driver, "/sq/ConfigCenter/MeasureSchematic/CheckMeasureSchematic.yaml");
        Reporter.log("<b><h2>CASE："+checkMeasureSchematic.CaseName()+"</h2></b>");
        checkMeasureSchematic.operate();
        Assert.assertTrue(checkMeasureSchematic.checkpoint(this.browserType), "检查点不通过");
    }

}
