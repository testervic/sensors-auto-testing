package test.sq.login;

import com.esotericsoftware.yamlbeans.YamlException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import pageobjects.LoginPage;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqLoginTest {
    private WebDriver driver;
    private String browserType = "chrome";
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:sss");//设置日期格式
    public SqLoginTest(WebDriver driver) {
        this.driver = driver;
    }

    //登录SQTEST
    public void sqLoginSQTEST() throws YamlException, FileNotFoundException, InterruptedException {
        LoginPage sqloginPage = new LoginPage(this.driver, "/sq/SqLogin/SqLogin-testSQ9.yaml");
        Reporter.log("<b><h2>CASE："+sqloginPage.CaseName()+df.format(new Date())+"</h2></b>");
        sqloginPage.operate();
        Assert.assertTrue(sqloginPage.checkpoint(this.browserType), "检查点不通过");
    }
}
