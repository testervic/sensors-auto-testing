package test.sq.uploadstaging.Excel.SystemExcel;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadSystemExcel extends BaseTestMethod {

    @Test(priority = 4)
    public void uploadSystemExcel() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //上传Excel系统模板
        PublicPage uploadSystemExcel = new PublicPage(this.driver, "/sq/UploadStaging/Excel/SystemExcel/SystemExcelUpload.yaml");
        Reporter.log("<b><h2>CASE："+uploadSystemExcel.CaseName()+"</h2></b>");
        uploadSystemExcel.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".ant-upload-select .ant-upload > input")).sendKeys(screenPath+"/testfile/SQAutoUI_SystemExcel.xlsx");
        Assert.assertTrue(uploadSystemExcel.checkpoint(this.browserType), "检查点不通过");
    }
}
