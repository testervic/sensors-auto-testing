package test.le.productmaterial;

import browser.TestBaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.le.login.LoginTest;

import java.io.File;
import java.io.IOException;

public class ExcelImportTest extends BaseTestMethod {

    //产品素材-批量导入
    @Test(priority = 1)
    public void excelImport() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage publicPage = new PublicPage(this.driver, "/le/ProductMaterial/BulkImport/ExcelImport.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        publicPage.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        Thread.sleep(3000);
        try {
            //上传Excel文件，避免频繁修改路径
            driver.findElement(By.xpath("//*[@id=\"excelFile\"]")).sendKeys(screenPath + "/testfile/ExcelImportTest-AutoUITest-ZW.xlsx");
            Thread.sleep(1000);
        } catch (Exception e) {
            Reporter.log("error:" + e);
        }

        //ExcelImport1
        PublicPage ExcelImport1 = new PublicPage(this.driver, "/le/ProductMaterial/BulkImport/ExcelImport1.yaml");
        Reporter.log("<b><h2>CASE：" + ExcelImport1.CaseName() + "</h2></b>");
        ExcelImport1.operate();
        Assert.assertTrue(ExcelImport1.checkpoint(this.browserType), "检查点不通过");
    }
}
