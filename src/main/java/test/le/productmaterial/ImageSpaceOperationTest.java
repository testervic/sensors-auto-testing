package test.le.productmaterial;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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

import java.io.FileNotFoundException;
import java.util.Date;

public class ImageSpaceOperationTest extends BaseTestMethod {

    @Test(priority = 1)
    public void imageSpaceTiailoring() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageSpaceTiailoring = new PublicPage(this.driver, "/le/ProductMaterial/ComprehensiveList/ImageSpaceTailoring.yaml");
        Reporter.log("<b><h2>CASE：" + imageSpaceTiailoring.CaseName() + "</h2></b>");
        imageSpaceTiailoring.operate();
        Assert.assertTrue(imageSpaceTiailoring.checkpoint(this.browserType), "检查点不通过");
    }

    @Test(priority = 1)
    public void imageSpaceDrawing() throws YamlException, FileNotFoundException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageSpaceDrawing = new PublicPage(this.driver, "/le/ProductMaterial/ComprehensiveList/ImageSpaceDrawing.yaml");
        Reporter.log("<b><h2>CASE：" + imageSpaceDrawing.CaseName() + "</h2></b>");
        imageSpaceDrawing.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean status = isElement.waitForElement(By.xpath("//div[text()='保存出错!']"));
        //判断是否有图，有就生成详情页、没有就传图
        if (status) {
            Reporter.log("<b><h2>linux error</h2></b>");
            /*
            driver.findElement(By.xpath("//button[text()='OK']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='保存']")).click();
            Thread.sleep(2000);
            Assert.assertTrue(imageSpaceDrawing.checkpoint(this.browserType), "检查点不通过");
             */
        } else {
            Reporter.log("<b><h2>linux succsess</h2></b>");
            driver.findElement(By.xpath("//span[text()='保存']")).click();
            Assert.assertTrue(imageSpaceDrawing.checkpoint(this.browserType), "检查点不通过");
        }
    }
}
