package test.le.homepage;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.LoginPage;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.le.login.LoginTest;
import util.Retry;

import java.io.FileNotFoundException;

public class HomePageCheckStagingTest extends BaseTestMethod {

    @Test(priority = 1)
    //字段录入-检查销售属性
    public void homePageCheck() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage publicPage = new PublicPage(this.driver, "/le/HomePage/HomePageCheck.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
}
