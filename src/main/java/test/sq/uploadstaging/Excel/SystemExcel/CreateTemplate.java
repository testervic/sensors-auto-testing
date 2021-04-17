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

public class CreateTemplate extends BaseTestMethod {

    @Test(priority = 1)
    public void createTemplate() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //将Excel系统模板中的商品加入编辑工作台
        PublicPage createTemplate = new PublicPage(this.driver, "/sq/UploadStaging/Excel/SystemExcel/CreateTemplate.yaml");
        Reporter.log("<b><h2>CASE："+createTemplate.CaseName()+"</h2></b>");
        createTemplate.operate();
        Assert.assertTrue(createTemplate.checkpoint(this.browserType), "检查点不通过");
    }
}
