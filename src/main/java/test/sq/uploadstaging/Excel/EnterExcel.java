package test.sq.uploadstaging.Excel;

import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import pageobjects.PublicPage;
import test.sq.login.SqLoginTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EnterExcel {
    private WebDriver driver;
    private String browserType = "chrome";
    public EnterExcel(WebDriver driver) {
        this.driver = driver;
    }

    public void enterExcel() throws YamlException, FileNotFoundException, InterruptedException, IOException {
        SqLoginTest sqLoginTest = new SqLoginTest(driver);
        sqLoginTest.sqLoginSQTEST();
        PublicPage enterExcel = new PublicPage(this.driver, "/sq/UploadStaging/Excel/EnterExcel.yaml");
        Reporter.log("<b><h2>CASE："+enterExcel.CaseName()+"</h2></b>");
        enterExcel.operate();
        Assert.assertTrue(enterExcel.checkpoint(this.browserType), "检查点不通过");
    }
}
