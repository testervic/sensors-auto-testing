package test.sq.configcenter.CategoriesConfig;

import com.esotericsoftware.yamlbeans.YamlException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckCategories extends BaseTestMethod {

    @Test(priority = 1)
    public void checkCategories() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage checkCategories = new PublicPage(this.driver, "/sq/ConfigCenter/CategoriesConfig/CheckCategories.yaml");
        Reporter.log("<b><h2>CASE："+checkCategories.CaseName()+"</h2></b>");
        checkCategories.operate();
        Assert.assertTrue(checkCategories.checkpoint(this.browserType), "检查点不通过");
    }
}
