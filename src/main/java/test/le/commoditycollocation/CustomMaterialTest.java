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

import java.io.IOException;

public class CustomMaterialTest extends BaseTestMethod {

    //商品互搭-自定义素材
    @Test(priority = 1)
    public void materialTemplate() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage materialTemplateCheck = new PublicPage(this.driver, "/le/CommodityCollocation/MaterialTemplateAddEdit.yaml");
        Reporter.log("<b><h2>CASE：" + materialTemplateCheck.CaseName() + "</h2></b>");
        materialTemplateCheck.operate();
        Assert.assertTrue(materialTemplateCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @Test(priority = 2)
    public void templatePut() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage templatePutCheck = new PublicPage(this.driver, "/le/CommodityCollocation/TemplatePut.yaml");
        Reporter.log("<b><h2>CASE：" + templatePutCheck.CaseName() + "</h2></b>");
        templatePutCheck.operate();
        Assert.assertTrue(templatePutCheck.checkpoint(this.browserType), "检查点不通过");
    }

    @Test(priority = 3)
    public void shutdownTemplatePut() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage shutdownTemplatePutCheck = new PublicPage(this.driver, "/le/CommodityCollocation/ShutdownTemplatePut.yaml");
        Reporter.log("<b><h2>CASE：" + shutdownTemplatePutCheck.CaseName() + "</h2></b>");
        shutdownTemplatePutCheck.operate();
        Assert.assertTrue(shutdownTemplatePutCheck.checkpoint(this.browserType), "检查点不通过");
    }
}
