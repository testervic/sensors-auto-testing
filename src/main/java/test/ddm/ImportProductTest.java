package test.ddm;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.PublicPage;

import java.io.FileNotFoundException;

public class ImportProductTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";


    @Parameters({ "browserType", "appURL" ,"browserVersion", "remoteIP"})
    @BeforeClass
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP,  ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL,browserVersion, remoteIP);
        this.browserType = browserType;
    }

    @Test(priority = 1)
    public void testLogin() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage ddmloginPage = new LoginPage(this.driver, "/ddm/DDMLogin.yaml");
        Reporter.log("<b><h2>CASE："+ddmloginPage.CaseName()+"</h2></b>");
        ddmloginPage.operate();
        Assert.assertTrue(ddmloginPage.checkpoint(this.browserType), "检查点不通过");

    }

    @Test(priority = 2)
    public void importExcel() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/ImportProduct.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();

//        String userdirPath = System.getProperty("user.dir");
//        if(userdirPath.endsWith("target")){
//            userdirPath = userdirPath.replace("target","");
//        }
//        String excelPath = userdirPath + "/res/" + "AutoTesting.xlsx";
//        driver.findElement(By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/a/span[2]")).click();  //点击产品素材
//        driver.findElement(By.xpath("/html/body/app-root/app-authorized/app-side-nav/div/div[1]/div/ul/li[2]")).click(); //点击素材网盘
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"body\"]/nz-spin/div/app-list/article/article/div/app-operation-list/div/button[4]")).click(); //点击导入商品
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("/html/body/nz-modal/div/div[2]/div/div/div[2]/app-upload-excel/nz-upload/div/div/input")).sendKeys(excelPath); //上传excel文件
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("/html/body/nz-modal/div/div[2]/div/div/div[2]/app-upload-excel/div/button[1]")).click(); //点击导入

        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");

    }

    @AfterClass
    public void tearDown() {

        this.driver.quit();
    }
}
