package test.le.productmaterial;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobjects.PublicPage;
import test.le.login.LoginTest;
import util.OperateElement;
import util.Retry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class DownloadExcelTemplateTest {
    private WebDriver driver;
    private TestBaseSetup testBaseSetup = new TestBaseSetup();
    private String browserType = "";
    public Logger log = Logger.getLogger(this.getClass().getName());

    //启动浏览器并打开链接
    @Parameters({"browserType", "appURL", "browserVersion", "remoteIP"})
    @BeforeMethod
    public void setUp(String browserType, String appURL, String browserVersion, String remoteIP, ITestContext testContext) {
        driver = testBaseSetup.setDriver(browserType, appURL, browserVersion, remoteIP);
        this.browserType = browserType;
    }

    //产品素材-批量导入-模板下载
    @Test(priority = 1, retryAnalyzer = Retry.class)
    public void downloadExcelTemplate() throws IOException, InterruptedException {
        //登录
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        //yaml用例路径
        PublicPage publicPage = new PublicPage(this.driver, "/le/ProductMaterial/BulkImport/DownloadExcelTemplate.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        //执行用例testcase
        publicPage.operate();
        //断言check
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }

    @Test(priority = 1)
    public void onlineEntry() throws IOException, InterruptedException {
        //登录
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLoginDDTEST();
        PublicPage CheckShopAuthorization = new PublicPage(this.driver, "/le/ProductMaterial/BulkImport/CheckShopAuthorization.yaml");
        PublicPage onlineEntry = new PublicPage(this.driver, "/le/ProductMaterial/BulkImport/OnlineEntry.yaml");
        Reporter.log("<b><h2>CASE：" + CheckShopAuthorization.CaseName() + "</h2></b>");
        //检查授权
        CheckShopAuthorization.operate();
        OperateElement isElement = new OperateElement(this.driver);
        Boolean authorization = isElement.waitForElement(By.xpath("//span[text()='宝贝地址对应的授权不完整']"));
        log.info("authorization:" + authorization);
        //判断是否有图，有就生成详情页、没有就传图
        if (!authorization) {
            Reporter.log("<b><h2>CASE：" + onlineEntry.CaseName() + "</h2></b>");
            //执行在线录入
            onlineEntry.operate();
            Assert.assertTrue(onlineEntry.checkpoint(this.browserType), "检查点不通过");
            Reporter.log("<b><h2>店铺已授权，继续在线录入CASE</h2></b>");
        } else {
            Reporter.log("<b><h2>店铺未授权，跳过在线录入CASE</h2></b>");
        }
    }

    //关闭浏览器
    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
