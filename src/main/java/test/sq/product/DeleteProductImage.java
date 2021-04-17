package test.sq.product;

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

public class DeleteProductImage extends BaseTestMethod {

    @Test(priority = 3,groups = {"third"})
    public void deleteProductImage() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteProductImage = new PublicPage(this.driver, "/sq/ProductManagement/DeleteProductImage.yaml");
        Reporter.log("<b><h2>CASE："+deleteProductImage.CaseName()+"</h2></b>");
        deleteProductImage.operate();
        Assert.assertTrue(deleteProductImage.checkpoint(this.browserType), "检查点不通过");
    }
}
