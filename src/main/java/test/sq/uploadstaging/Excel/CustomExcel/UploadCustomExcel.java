package test.sq.uploadstaging.Excel.CustomExcel;

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

public class UploadCustomExcel extends BaseTestMethod {

    @Test(priority = 4)
    public void customExcelUpload() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        //上传Excel自定义模板
        PublicPage customExcelUpload = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/CustomExcelUpload.yaml");
        Reporter.log("<b><h2>CASE："+customExcelUpload.CaseName()+"</h2></b>");
        customExcelUpload.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".ant-upload-select .ant-upload > input")).sendKeys(screenPath+"/testfile/SQAutoUICustomExcel.xlsx");
        Assert.assertTrue(customExcelUpload.checkpoint(this.browserType), "检查点不通过");
    }
}
