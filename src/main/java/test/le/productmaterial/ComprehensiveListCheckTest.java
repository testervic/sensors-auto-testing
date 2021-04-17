package test.le.productmaterial;

import browser.TestBaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.le.login.LoginTest;
import util.Retry;

import java.io.IOException;

public class ComprehensiveListCheckTest extends BaseTestMethod {

    //产品素材-批量导入-模板下载
    @Test(priority = 1, retryAnalyzer = Retry.class)
    public void comprehensiveListCheck() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage publicPage = new PublicPage(this.driver, "/le/ProductMaterial/ComprehensiveList/ComprehensiveListCheck.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }

}
