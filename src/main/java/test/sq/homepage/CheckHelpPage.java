package test.sq.homepage;

import com.esotericsoftware.yamlbeans.YamlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;

public class CheckHelpPage extends BaseTestMethod {
    @Test(priority = 1)
    public void checkHelpPage() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkHelpPage = new PublicPage(this.driver, "/sq/HomePage/HelpPageCheck.yaml");
        Reporter.log("<b><h2>CASE："+checkHelpPage.CaseName()+"</h2></b>");
        checkHelpPage.operate();
        Assert.assertTrue(checkHelpPage.checkpoint(this.browserType), "检查点不通过");
    }

}
