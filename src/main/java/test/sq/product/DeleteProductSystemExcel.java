package test.sq.product;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;

public class DeleteProductSystemExcel extends BaseTestMethod {

    @Test(priority = 3,groups = {"low"})
    public void deleteProductSystemExcel() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteProductSystemExcel = new PublicPage(this.driver, "/sq/ProductManagement/DeleteProductSystemExcel.yaml");
        Reporter.log("<b><h2>CASE："+deleteProductSystemExcel.CaseName()+"</h2></b>");
        deleteProductSystemExcel.operate();
        Assert.assertTrue(deleteProductSystemExcel.checkpoint(this.browserType), "检查点不通过");
    }
}
