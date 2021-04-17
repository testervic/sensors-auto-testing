package test.sq.uploadstaging.Excel.CustomExcel;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestClass;
import test.sq.uploadstaging.Excel.EnterExcel;

import java.io.File;
import java.io.IOException;

public class AddCustomExcelTemplate extends BaseTestClass {
    @Test(priority = 1)
    public void uploadCustomExcelTemplate() throws InterruptedException, IOException {
        EnterExcel enterExcel = new EnterExcel(driver);
        enterExcel.enterExcel();
        //切换frame
        driver.switchTo().frame(0);
        PublicPage uploadCustomExcelTemplate = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/UploadCustomExcelTemplate.yaml");
        Reporter.log("<b><h2>CASE："+uploadCustomExcelTemplate.CaseName()+"</h2></b>");
        uploadCustomExcelTemplate.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        driver.findElement(By.cssSelector(".ng-star-inserted .ant-upload-btn > input")).sendKeys(screenPath+"/testfile/SQCustomExcelTemplate.xlsx");
        Assert.assertTrue(uploadCustomExcelTemplate.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 2,groups = "first")
    public void createCustomExcelTemplate() throws InterruptedException, IOException {
        PublicPage createCustomExcelTemplate = new PublicPage(this.driver, "/sq/UploadStaging/Excel/CustomExcel/CreateCustomExcelTemplate.yaml");
        Reporter.log("<b><h2>CASE："+createCustomExcelTemplate.CaseName()+"</h2></b>");
        createCustomExcelTemplate.operate();
        Assert.assertTrue(createCustomExcelTemplate.checkpoint(this.browserType), "检查点不通过");
    }
}
