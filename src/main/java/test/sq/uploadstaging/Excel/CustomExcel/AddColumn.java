package test.sq.uploadstaging.Excel.CustomExcel;

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
import test.sq.uploadstaging.Excel.EnterExcel;

import java.io.IOException;

public class AddColumn extends BaseTestMethod {

    @Test(priority = 3,groups = "second")
    public void addColumn() throws InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        PublicPage addColumn = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/AddColumn.yaml");
        Reporter.log("<b><h2>CASE："+addColumn.CaseName()+"</h2></b>");
        addColumn.operate();
        Assert.assertTrue(addColumn.checkpoint(this.browserType), "检查点不通过");
    }
}
