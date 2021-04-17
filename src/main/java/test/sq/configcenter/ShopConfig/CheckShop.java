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

public class CheckShop extends BaseTestMethod {

    @Test(priority = 1,groups = "high")
    public void checkShop() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkShop = new PublicPage(this.driver, "/sq/ConfigCenter/ShopConfig/CheckShop.yaml");
        Reporter.log("<b><h2>CASE："+checkShop.CaseName()+"</h2></b>");
        checkShop.operate();
        Assert.assertTrue(checkShop.checkpoint(this.browserType), "检查点不通过");
    }

}
