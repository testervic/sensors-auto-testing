package test.sq.uploadstaging.Excel.SystemExcel;

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

public class SystemExcelAddEdit extends BaseTestMethod {

    @Test(priority = 1,groups = {"high"})
    public void systemExcelAddEdit() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //将Excel系统模板中的商品加入编辑工作台
        PublicPage systemExcelAddEdit = new PublicPage(this.driver, "/sq/UploadStaging/Excel/SystemExcel/SystemExcelAddEdit.yaml");
        Reporter.log("<b><h2>CASE："+systemExcelAddEdit.CaseName()+"</h2></b>");
        systemExcelAddEdit.operate();
        Assert.assertTrue(systemExcelAddEdit.checkpoint(this.browserType), "检查点不通过");
    }
}
