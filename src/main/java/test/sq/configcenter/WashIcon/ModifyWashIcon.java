package test.sq.configcenter.WashIcon;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ModifyWashIcon extends BaseTestClass {

    @Test(priority = 2,groups = "middle")
    public void modifyWashIcon() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage modifyWashIcon = new PublicPage(this.driver, "/sq/ConfigCenter/WashIcon/ModifyWashIcon.yaml");
        Reporter.log("<b><h2>CASE："+modifyWashIcon.CaseName()+"</h2></b>");
        modifyWashIcon.operate();
        Assert.assertTrue(modifyWashIcon.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 3)
    public void deleteWashIcon() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        PublicPage deleteWashIcon = new PublicPage(this.driver, "/sq/ConfigCenter/WashIcon/DeleteWashIcon.yaml");
        Reporter.log("<b><h2>CASE："+deleteWashIcon.CaseName()+"</h2></b>");
        deleteWashIcon.operate();
        Assert.assertTrue(deleteWashIcon.checkpoint(this.browserType), "检查点不通过");
    }

}
