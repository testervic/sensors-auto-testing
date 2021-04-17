package test.le.login;

import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import pageobjects.LoginPage;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest {
    private WebDriver driver;
    private String browserType = "chrome";
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    //登录molly
    public void leLogin() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-molly.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录works
    public void leLoginWorks() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-works.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录hdwtest
    public void leLoginHdwtest() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-hdwtest.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录deepdraw
    public void leLoginDeepdraw() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-deepdraw.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录DDTEST
    public void leLoginDDTEST() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-DDTEST.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录testchild
    public void leLoginTestchild() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-testchild.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录tryout1
    public void leLogintTryout1() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-tryout1.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

    //登录testhdw
    public void leLoginTesthdw() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/le/Common/LeLogin-testhdw.yaml");
        Reporter.log("<b><h2>CASE：" + leloginPage.CaseName() + df.format(new Date()) + "</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

}
