package test.le.productmaterial;

import browser.TestBaseSetup;
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
import test.le.login.LoginTest;

import java.io.File;
import java.io.IOException;


public class ExcelSystemImportTest extends BaseTestMethod {

    //    产品素材-上传excel文件-系统模板下载
    @Test(priority = 1)
    public void downloadSystemTemplateExcel() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage downloadSystemTemplateExcel = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/DownloadSystemTemplateExcel.yaml");
        Reporter.log("<b><h2>CASE：" + downloadSystemTemplateExcel.CaseName() + "</h2></b>");
        downloadSystemTemplateExcel.operate();
        Assert.assertTrue(downloadSystemTemplateExcel.checkpoint(this.browserType), "检查点不通过");
    }

    //    产品素材-上传excel文件-系统模板上传
    @Test(priority = 1)
    public void uploadSystemTemplateExcel() throws IOException, InterruptedException {
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage uploadSystemTemplateExcel = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/UploadSystemTemplateExcel.yaml");
        Reporter.log("<b><h2>CASE：" + uploadSystemTemplateExcel.CaseName() + "</h2></b>");
        uploadSystemTemplateExcel.operate();
        try {
            Reporter.log("<br></br><b>STEP：开始上传系统EXCEL模板</b>");
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/app-root/app-upload/div/app-upload-sku/app-system-templates/div/div[2]/div[1]/div[1]/nz-upload/div/div/input")).sendKeys(screenPath + "/testfile/ExcelSystemImportTest-AutoUITest-ZW.xlsx");
            Assert.assertTrue(uploadSystemTemplateExcel.checkpoint(this.browserType), "检查点不通过");
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }

    }

    //创建/更新商品
    @Test(priority = 2)
    public void systemTemplateUpdateProduct() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage downloadSystemTemplateExcel = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/SystemTemplateUpdateProduct.yaml");
        Reporter.log("<b><h2>CASE：" + downloadSystemTemplateExcel.CaseName() + "</h2></b>");
        downloadSystemTemplateExcel.operate();
        Assert.assertTrue(downloadSystemTemplateExcel.checkpoint(this.browserType), "检查点不通过");
    }
}
