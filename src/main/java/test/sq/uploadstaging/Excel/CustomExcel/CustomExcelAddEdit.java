package test.sq.uploadstaging.Excel.CustomExcel;

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
import test.sq.uploadstaging.Excel.EnterExcel;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomExcelAddEdit extends BaseTestMethod {

    @Test(priority = 1,groups = {"high"})
    public void customExcelAddEdit() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //将Excel自定义模板中的商品加入编辑工作台
        PublicPage customExcelAddEdit = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/CustomExcelAddEdit.yaml");
        Reporter.log("<b><h2>CASE："+customExcelAddEdit.CaseName()+"</h2></b>");
        customExcelAddEdit.operate();
        Assert.assertTrue(customExcelAddEdit.checkpoint(this.browserType), "检查点不通过");
    }
}
