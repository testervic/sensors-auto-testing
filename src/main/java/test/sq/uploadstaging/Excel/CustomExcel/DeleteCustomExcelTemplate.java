package test.sq.uploadstaging.Excel.CustomExcel;

import browser.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.uploadstaging.Excel.EnterExcel;

import java.io.File;
import java.io.IOException;

public class DeleteCustomExcelTemplate extends BaseTestMethod {

    @Test(priority = 5,groups = "fourth")
    public void deleteCustomExcelTemplate() throws InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        PublicPage deleteCustomExcelTemplate = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/DeleteCustomExcelTemplate.yaml");
        Reporter.log("<b><h2>CASE："+deleteCustomExcelTemplate.CaseName()+"</h2></b>");
        deleteCustomExcelTemplate.operate();
        Assert.assertTrue(deleteCustomExcelTemplate.checkpoint(this.browserType), "检查点不通过");
    }
}
