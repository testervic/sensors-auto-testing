package test.le.productmaterial;

import browser.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
import util.OperateElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExcelCustomImportTest extends BaseTestMethod {

    //    产品素材-上传excel文件-自定义模板创建
    @Test(priority = 1)
    public void addCustomTemplateExcel() throws IOException, InterruptedException {
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage addCustomTemplateExcel = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/AddCustomTemplateExcel.yaml");
        PublicPage addCustomTemplateExcel1 = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/AddCustomTemplateExcel1.yaml");
        Reporter.log("<b><h2>CASE：" + addCustomTemplateExcel.CaseName() + "</h2></b>");
        addCustomTemplateExcel.operate();
        try {
            Reporter.log("<br></br><b>STEP：开始上传自定义EXCEL模板</b>");
            Thread.sleep(1000);
            //上传自定义模板
            driver.findElement(By.xpath("/html/body/app-root/app-upload/div/app-customize-templates-creation/div/app-creation/div/div[2]/app-step-uploads/div[1]/nz-upload/div/div/input")).sendKeys(screenPath + "/testfile/addCustomTemplateExcel-AutoUITest-ZW.xlsx");
            Reporter.log("<b><h2>CASE：" + addCustomTemplateExcel1.CaseName() + "</h2></b>");
            addCustomTemplateExcel1.operate();
            Assert.assertTrue(addCustomTemplateExcel1.checkpoint(this.browserType), "检查点不通过");
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
    }

    //    产品素材-上传excel文件-自定义模板上传
    @Test(priority = 1, groups = {"customTemplateImportExcel"})
    public void customTemplateImportExcel() throws IOException, InterruptedException {
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage customTemplateImportExcel = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/CustomTemplateImportExcel.yaml");
        PublicPage addCustomTemplateExcel1 = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/CustomTemplateImportExcel1.yaml");
        Reporter.log("<b><h2>CASE：" + customTemplateImportExcel.CaseName() + "</h2></b>");
        customTemplateImportExcel.operate();
        try {
            Reporter.log("<br></br><b>STEP：开始上传自定义EXCEL模板</b>");
            Thread.sleep(3000);
            //上传自定义模板
            driver.findElement(By.xpath("/html/body/app-root/app-upload/div/app-upload-sku/app-customized-templates/nz-spin/div/div/div[1]/div[1]/nz-upload/div/div/input")).sendKeys(screenPath + "/testfile/ExcelCustomImportTest-AutoUITest-ZW.xlsx");
            Thread.sleep(1000);
            Reporter.log("<b><h2>CASE：" + addCustomTemplateExcel1.CaseName() + "</h2></b>");
            addCustomTemplateExcel1.operate();
            Assert.assertTrue(addCustomTemplateExcel1.checkpoint(this.browserType), "检查点不通过");
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }
    }

    //创建/更新商品
    @Test(priority = 2, groups = {"customTemplateUpdateProduct"})
    public void customTemplateUpdateProduct() throws IOException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式
        String dateNow = df.format(new Date());
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage customTemplateUpdateProductCheck = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/CustomTemplateUpdateProductCheck.yaml");
        PublicPage customTemplateUpdateProduct = new PublicPage(this.driver, "/le/ProductMaterial/UploadExcelFile/CustomTemplateUpdateProduct.yaml");
        Reporter.log("<b><h2>CASE：" + customTemplateUpdateProductCheck.CaseName() + "</h2></b>");
        customTemplateUpdateProductCheck.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean status = isElement.waitForElement(By.xpath("//td[text()='AutoUICustom-5']"));
        //判断是否有图，有就生成详情页、没有就传图
        if (status) {
            Reporter.log("<b><h2>CASE：" + customTemplateUpdateProduct.CaseName() + " " + df.format(new Date()) + "</h2></b>");
            customTemplateUpdateProduct.operate();
            Assert.assertTrue(customTemplateUpdateProduct.checkpoint(this.browserType), "检查点不通过");
        } else {
            Reporter.log("<b><h2>分析还未完成，跳过创建商品" + df.format(new Date()) + "</h2></b>");
        }
    }
}
