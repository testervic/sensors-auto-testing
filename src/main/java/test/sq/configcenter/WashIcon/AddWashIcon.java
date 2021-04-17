package test.sq.configcenter.WashIcon;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.BaseTestMethod;
import test.sq.login.SqLoginTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddWashIcon extends BaseTestMethod {

    @Test(priority = 1,groups = "high")
    public void addWashIcon() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage addWashIcon = new PublicPage(this.driver, "/sq/ConfigCenter/WashIcon/AddWashIcon.yaml");
        Reporter.log("<b><h2>CASE："+addWashIcon.CaseName()+"</h2></b>");
        addWashIcon.operate();
        File directory = new File("res");
        String screenPath = directory.getCanonicalPath();
        for (int i=1;i<29;i++){
            driver.findElement(By.xpath("//*[@id='icons']/app-icon-item[" + i + "]/form/div/nz-upload/div/div/input")).sendKeys(screenPath+"/testfile/AutoTesting.png");
            for(int j=1;j<3;j++) {
                driver.findElement(By.xpath("//*[@id='icons']/app-icon-item[" + i + "]/form/div[2]/nz-form-item/nz-form-control["+j+"]/div/span/input")).sendKeys("AutoUITest");
            }
        }
        driver.findElement(By.xpath("//*[contains(@class, 'ant-modal-footer')]//*[contains(@class, 'ant-btn-primary')]")).click();
        Assert.assertTrue(addWashIcon.checkpoint(this.browserType), "检查点不通过");
    }

}
