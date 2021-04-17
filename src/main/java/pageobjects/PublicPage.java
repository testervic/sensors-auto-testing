package pageobjects;

import com.esotericsoftware.yamlbeans.YamlException;
import io.qameta.allure.Attachment;
import model.CheckPoint;
import model.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import util.ExtentTestNGIReporterListener;
import util.OperateElement;
import util.YamlRead;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PublicPage {
    YamlRead yamlRead;
    OperateElement operateElement;
    protected WebDriver driver;
    private boolean isOperate = true;
    public Logger log = Logger.getLogger(this.getClass().getName());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
    String dateNow = df.format(new Date());
    /***
     * 默认构造函数
     * @param driver
     * @param path yaml配置参数
     */
    public PublicPage(WebDriver driver, String path) {
        this.driver = driver;
        yamlRead = new YamlRead(path);
        operateElement= new OperateElement(this.driver);
    }

    public String CaseName() throws YamlException, FileNotFoundException {
        List list = (List) yamlRead.getYmal().get("title");
        for (Object item : list) {
            TestCase testCase = new TestCase();
            testCase.setCase_name((String) ((Map)item).get("case_name"));
            String caseName = testCase.getCaseName();
            return caseName;
        }
        return null;
    }

    /***
     * 测试步骤
     * @throws YamlException
     * @throws FileNotFoundException
     */
    public void operate() throws YamlException, FileNotFoundException, InterruptedException {
//        prefix();
        List list = (List) yamlRead.getYmal().get("testcase");
        try {
            for (Object item : list) {
                TestCase testCase = new TestCase();
                testCase.setFind_type((String) ((Map) item).get("find_type"));
                testCase.setElement_info((String) ((Map) item).get("element_info"));
                testCase.setText((String) ((Map) item).get("text"));
                testCase.setOperate_type((String) ((Map) item).get("operate_type"));
                testCase.setStep((String) ((Map) item).get("step"));
                testCase.setWait_time((String) ((Map) item).get("wait_time"));
                testCase.setSwitch_window((String) ((Map) item).get("switch_window"));
                testCase.setFrame((String) ((Map) item).get("frame"));
                testCase.setDefaultContent((String) ((Map) item).get("defaultContent"));
                if (!operateElement.operate(testCase)) {
                    isOperate = false;
                    log.info("操作失败:"+item);
//                TakesScreenshot("fail");
                    break;
                }
            }
        }
        catch (org.openqa.selenium.NoSuchFrameException e) {
            TakesScreenshot("Exception");
            makeScreenshotOnFailure();
            log.info("切换frame失败");
        }
    }

    /***
     * 检查点
     * @return
     * @throws YamlException
     * @throws FileNotFoundException
     */
    public boolean checkpoint(String browserType) throws YamlException, FileNotFoundException, InterruptedException {
        List list = (List) yamlRead.getYmal().get("check");
        CheckPoint checkPoint = new CheckPoint();
        for(Object item: list){
//                CheckPoint checkPoint = new CheckPoint();
                checkPoint.setElement_info((String) ((Map)item).get("element_info"));
                checkPoint.setFind_type((String) ((Map)item).get("find_type"));
                checkPoint.setStep((String) ((Map)item).get("step"));
                checkPoint.setText((String) ((Map)item).get("text"));
                checkPoint.setOperate_type((String) ((Map)item).get("operate_type"));
                checkPoint.setWait_time((String) ((Map)item).get("wait_time"));
                checkPoint.setSwitch_window((String) ((Map)item).get("switch_window"));
                if (!operateElement.checkElement(checkPoint)) {
                    log.info("checkPoint:"+checkPoint.getElement_info());
                    Reporter.log("<br></br>CHECK:"+checkPoint.getElement_info());
                    TakesScreenshot(browserType);
                    makeScreenshotOnFailure();
                    return false;
                }
            Reporter.log("<br></br>CHECK:"+checkPoint.getElement_info());
            Reporter.log("<br>STEP:"+checkPoint.getStep());
            Reporter.log("<br><strong><font size=\"4\">PASS！<br></br></font></strong>");
            }
        return true;
    }
    @Attachment("Screenshot on failure")
    public byte[] makeScreenshotOnFailure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void TakesScreenshot(String browserType) {
        File directory = new File("target/surefire-reports/html");
        try {
            String screenPath = directory.getCanonicalPath() + "/screenshot/";
            String[] projectName = screenPath.split("/");
            File file = new File(screenPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            log.info("------------检查点失败截图--------");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
            String dateNow = df.format(new Date());
            String fileName = screenPath + browserType + "_" + dateNow + ".png";
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            driver.switchTo().defaultContent();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(fileName));
            ExtentTestNGIReporterListener.fileName = fileName;
            String os = System.getProperty("os.name");
//            Reporter.setCurrentTestResult(result);
            Reporter.log("<br>截图目录："+file.getAbsolutePath());
            Reporter.log("<br>失败截图："+browserType + "_" + dateNow + ".png"+"<br>");
            if(os.toLowerCase().startsWith("linux")){
                String prctureUrl = "http://47.99.179.79:10080/job/"+projectName[5]+"/reportNG-HTML/";
                String picurePath = prctureUrl+"/screenshot/"+browserType + "_" + dateNow + ".png";
                String html="<img src='"+picurePath+"' onclick='window.open(\""+picurePath+"\")'' hight='400' width='400'/>";
                Reporter.log(html);
            }else{
                String html="<img src='"+fileName+"' onclick='window.open(\""+fileName+"\")'' hight='400' width='400'/>";
                Reporter.log(html);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(ITestResult result) throws Exception {
//        if (!result.isSuccess())
//            catchExceptions(result);
//    }
//
//    public void catchExceptions(ITestResult result) {
//        System.out.println("result" + result);
//        String methodName = result.getName();
//        System.out.println(methodName);
//        if (!result.isSuccess()) {
//            File file = new File("snapshot1");
//            Reporter.setCurrentTestResult(result);
//            System.out.println(file.getAbsolutePath());
//            Reporter.log(file.getAbsolutePath());
//            String filePath = file.getAbsolutePath();
//            String dest = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
//            String picName = filePath + File.separator + dest;
//            String escapePicName = escapeString(picName);
//            System.out.println(escapePicName);
//            String html = "<img src='" + picName + ".png' onclick='window.open(\"" + escapePicName + ".png\")'' hight='100' width='100'/>";
//            Reporter.log(html);
//        }
//    }
}