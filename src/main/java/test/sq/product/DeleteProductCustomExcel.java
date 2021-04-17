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

public class DeleteProductCustomExcel extends BaseTestMethod {

    @Test(priority = 3,groups = {"low"})
    public void deleteProductCustomExcel() throws YamlException, FileNotFoundException, InterruptedException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage deleteProductCustomExcel = new PublicPage(this.driver, "/sq/ProductManagement/DeleteProductCustomExcel.yaml");
        Reporter.log("<b><h2>CASE："+deleteProductCustomExcel.CaseName()+"</h2></b>");
        deleteProductCustomExcel.operate();
        Assert.assertTrue(deleteProductCustomExcel.checkpoint(this.browserType), "检查点不通过");
    }
}
