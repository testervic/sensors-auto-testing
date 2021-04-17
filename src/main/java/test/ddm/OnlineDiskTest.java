package test.ddm;

import browser.TestBaseSetup;
import com.esotericsoftware.yamlbeans.YamlException;
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

public class OnlineDiskTest {
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
    public void uploadPicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/UploadPicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 3)
    public void renamePicture() throws YamlException, FileNotFoundException, InterruptedException{
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/RenamePicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 4)
    public void SetLabel() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/SetLabel.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 5)
    public void sharePicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/SharePicture.yaml");
        Reporter.log("<b><h2>CASE：" + publicPage.CaseName() + "</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
        Thread.sleep(2000);
    }
    @Test(priority = 6)
    public void copyPicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/CopyPicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 7)
    public void movePicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/MovePicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @Test(priority = 8)
    public void deletePicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/DeletePicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
        driver.navigate().refresh();
    }
/*    @Test(priority = 9)
    public void uploadFolder() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/UploadFolder.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }*/
    @Test(priority = 10)
    public void createFolder() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/customizeCreateFolder.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
/*    @Test(priority = 11)
    public void customizeUploadFolder() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/customizeUploadFolder.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }*/
    @Test(priority = 12)
    public void customizeUploadPicture() throws YamlException, FileNotFoundException, InterruptedException {
        PublicPage publicPage = new PublicPage(this.driver, "/ddm/OnlineDisk/customizeUploadPicture.yaml");
        Reporter.log("<b><h2>CASE："+publicPage.CaseName()+"</h2></b>");
        publicPage.operate();
        Assert.assertTrue(publicPage.checkpoint(this.browserType), "检查点不通过");
    }
    @AfterClass
    public void tearDown() {

        this.driver.quit();
    }
}
