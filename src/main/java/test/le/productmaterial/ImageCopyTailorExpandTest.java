package test.le.productmaterial;

import browser.TestBaseSetup;
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

import java.io.IOException;

public class ImageCopyTailorExpandTest extends BaseTestMethod {

    //产品素材-综合列表-图片-单张复制和一键复制
    @Test(priority = 1)
    public void imageSingleCopy() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageSingleCopyCheck = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageCopy.yaml");
        Reporter.log("<b><h2>CASE：" + imageSingleCopyCheck.CaseName() + "</h2></b>");
        imageSingleCopyCheck.operate();
        Assert.assertTrue(imageSingleCopyCheck.checkpoint(this.browserType), "检查点不通过");
    }


    //产品素材-综合列表-图片-裁剪和扩边
    @Test(priority = 2)
    public void imageExpandTailor() throws IOException, InterruptedException {
        LoginTest leLogin = new LoginTest(this.driver);
        leLogin.leLogin();
        PublicPage imageExpandTailorCheck = new PublicPage(this.driver, "/le/ProductMaterial/ImageManager/ImageTailorExpand.yaml");
        Reporter.log("<b><h2>CASE：" + imageExpandTailorCheck.CaseName() + "</h2></b>");
        imageExpandTailorCheck.operate();
        Assert.assertTrue(imageExpandTailorCheck.checkpoint(this.browserType), "检查点不通过");
    }
}
