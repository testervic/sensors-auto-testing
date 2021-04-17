package test.le.commoditycollocation;

import browser.TestBaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.le.login.LoginTest;
import util.Retry;

import java.io.IOException;

public class FailureMatchRetrievalTest extends BaseTestMethod {

    //商品互搭-自定义素材-失效搭配检索
    @Test(priority = 1)
    public void failureMatchRetrieval() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage failureMatchRetrievalCheck = new PublicPage(this.driver, "/le/CommodityCollocation/FailureMatchRetrieval.yaml");
        Reporter.log("<b><h2>CASE：" + failureMatchRetrievalCheck.CaseName() + "</h2></b>");
        failureMatchRetrievalCheck.operate();
        Assert.assertTrue(failureMatchRetrievalCheck.checkpoint(this.browserType), "检查点不通过");
    }
}
