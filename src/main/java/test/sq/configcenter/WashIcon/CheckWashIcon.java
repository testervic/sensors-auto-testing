package test.sq.configcenter.WashIcon;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckWashIcon extends BaseTestMethod {

    @Test(priority = 1)
    public void checkWashIcon() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkWashIcon = new PublicPage(this.driver, "/sq/ConfigCenter/WashIcon/CheckSystemWashIcon.yaml");
        Reporter.log("<b><h2>CASE："+checkWashIcon.CaseName()+"</h2></b>");
        checkWashIcon.operate();
        Assert.assertTrue(checkWashIcon.checkpoint(this.browserType), "检查点不通过");
    }

}
