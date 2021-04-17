package test.ddm;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class SearchCenterTest {
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
    public void ImportProductName() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/SearchCenter/ImportProductName.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
        driver.navigate().refresh();
    }
    @Test(priority = 3)
    public void SelectProductAttribute() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/SearchCenter/SelectProductAttribute.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 4)
    public void SelectFileName() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/SearchCenter/SelectFileName.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
/*    @Test(priority = 4)
    public void SelectProductLabel() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/SearchCenter/SelectProductLabel.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }*/
    @AfterClass
    public void tearDown() {

        this.driver.quit();
    }
}
