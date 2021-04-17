package test.demo;

import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import pageobjects.LoginPage;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoLoginTest {
    private WebDriver driver;
    private String browserType = "chrome";
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式
    public DemoLoginTest(WebDriver driver) {
        this.driver = driver;
    }

    //登录molly
    public void demoLogin() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage leloginPage = new LoginPage(this.driver, "/demo/DemoLogin.yaml");
        Reporter.log("<b><h2>CASE："+leloginPage.CaseName()+df.format(new Date())+"</h2></b>");
        leloginPage.operate();
        Assert.assertTrue(leloginPage.checkpoint(this.browserType), "检查点不通过");
    }

}
