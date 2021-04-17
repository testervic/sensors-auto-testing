package test.sq.configcenter.ShopConfig;

import com.esotericsoftware.yamlbeans.YamlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddGeneralField extends BaseTestMethod {

    @Test(priority = 2,groups = "middle")
    public void addGeneralField() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage addGeneralField = new PublicPage(this.driver, "/sq/ConfigCenter/ShopConfig/AddGeneralField.yaml");
        Reporter.log("<b><h2>CASE："+addGeneralField.CaseName()+"</h2></b>");
        addGeneralField.operate();
        Assert.assertTrue(addGeneralField.checkpoint(this.browserType), "检查点不通过");
    }

}
