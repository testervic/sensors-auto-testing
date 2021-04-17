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

public class DeleteTemplate extends BaseTestMethod {

    @Test(priority = 2)
    public void deleteTemplate() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //将Excel系统模板中的商品加入编辑工作台
        PublicPage deleteTemplate = new PublicPage(this.driver, "/sq/UploadStaging/Excel/SystemExcel/DeleteTemplate.yaml");
        Reporter.log("<b><h2>CASE："+deleteTemplate.CaseName()+"</h2></b>");
        deleteTemplate.operate();
        Assert.assertTrue(deleteTemplate.checkpoint(this.browserType), "检查点不通过");
    }
}
