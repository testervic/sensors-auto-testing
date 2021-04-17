package browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class TestBaseSetup {
    private WebDriver driver = null;
    public Logger log = Logger.getLogger(this.getClass().getName());
    public WebDriver setDriver(String browserType, String appURL, String version, String remoteip){
        if (browserType.equals("chrome")) {
            driver = initChromeDriver(appURL, version, remoteip);
        } else if (browserType.equals("firefox")) {
            driver = initFirefoxDriver(appURL, version, remoteip);
        } else {
            log.info("browser : " + browserType
                    + " is invalid, Launching Firefox as browser of choice..");
            driver = initFirefoxDriver(appURL, version, remoteip);
        }
        return driver;
    }


    private WebDriver initChromeDriver(String appURL, String chrome_version, String remoteip){
        try{
            File directory = new File("res");
            String screenPath = directory.getCanonicalPath();
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("mac")){
                System.setProperty("webdriver.chrome.driver",screenPath+"/chromedriver/chromedriver_mac_v86");
            }else if(os.toLowerCase().startsWith("linux")){
                System.setProperty("webdriver.chrome.driver",screenPath+"/chromedriver/chromedriver_linux_v72");
            }else if(os.toLowerCase().startsWith("win")){
                System.setProperty("webdriver.chrome.driver",screenPath+"/chromedriver/chromedriver_win_v87.exe");
            }else{
                System.setProperty("webdriver.chrome.driver",screenPath+"/chromedriver/chromedriver_win_v87.exe");
            }
            if (Objects.equals(chrome_version, "") || Objects.equals(remoteip, "")) {
                ChromeOptions options = new ChromeOptions();
                String browserModel = "--headless";
                if(os.toLowerCase().startsWith("linux")){
                    //linux运行无头模式
                    options.addArguments(browserModel);
                }
                //其他平台默认开启，关闭请注释该行
//                options.addArguments(browserModel);
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().window().setSize(new Dimension(1920, 1080));
                driver.navigate().to(appURL);
            } else {
                DesiredCapabilities chromeDesiredcap = DesiredCapabilities.chrome();
                chromeDesiredcap.setVersion(chrome_version);
                try {
                    driver = new RemoteWebDriver(new URL("http://" + remoteip + ":4444/wd/hub/"), chromeDesiredcap);
                    driver.manage().window().maximize();
                    driver.navigate().to(appURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;

    }

    private WebDriver initFirefoxDriver(String appURL, String version, String remoteip) {
        if (Objects.equals(version, "") || Objects.equals(remoteip, "")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to(appURL);
        } else {
            DesiredCapabilities ffDesiredcap= new DesiredCapabilities("firefox", version, Platform.LINUX);
            ffDesiredcap.setVersion(version);
            try {
                driver = new RemoteWebDriver(new URL("http://" + remoteip + ":4444/wd/hub/"), ffDesiredcap);
                driver.manage().window().maximize();
                driver.navigate().to(appURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        log.info("Launching Firefox browser..");
        return driver;
    }
}