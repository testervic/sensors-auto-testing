package util;
import io.qameta.allure.Attachment;
import model.CheckPoint;
import model.TestCase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 常用元素操作类
 */
public class OperateElement {
    private WebDriver driver;
    private final int TIMEOUT = 15;
    private final int SLEEPLNMILLIS = 1;
    private static final String NAME  = "name";
    private static final String XPATH  = "xpath";
    private static final String CLASSNAME  = "className";
    private static final String CSSSELECTOR  = "cssSelector";
    private static final String ID  = "id";
    public Logger log = Logger.getLogger(this.getClass().getName());
    WebDriverWait wait;
    WebBrower webBrower;
    SimpleDateFormat df = new SimpleDateFormat("HHmmss");//设置日期格式
    String dateNow = df.format(new Date());
    public OperateElement(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,TIMEOUT,SLEEPLNMILLIS);
        webBrower = new WebBrower(this.driver);
    }



    /***
     *
     *
     * @param checkPoint 检查点实体类
     * @return
     */
    public Boolean checkElement(CheckPoint checkPoint) throws InterruptedException {
        boolean status = false;
        final By by = getElement(checkPoint.getFind_type(), checkPoint.getElement_info());
        if(checkPoint.getElement_info() !=null && checkPoint.getFind_type()!=null){
            status = waitForElement(by);
        }

        if(checkPoint.getWait_time() !=null ){
            try{
                long s  = Long.valueOf(checkPoint.getWait_time());
                Reporter.log("<br>SLEEP："+checkPoint.getWait_time()+"ms");
                Thread.sleep(s);
            }
            catch (Exception e){
                Reporter.log("wait time error: "+e);
            }
        }
        if(checkPoint.getSwitch_window()!= null){
            Reporter.log("<br>切换至当前窗口");
            //获取当前窗口句柄
            String win = driver.getWindowHandle();
            //获取所有窗口句柄
            Set<String> Windows = driver.getWindowHandles();
            //把获取到的窗口句柄放到list中
            List<String> allWindows = new ArrayList<String>(Windows);
            //切换到新打开的窗口并最大化
            int j = 0;
            for (int i = 0; i < allWindows.size(); i++) {
                if (!allWindows.get(i).equals(win)) {
                    j = i;
                }
            }
            driver.switchTo().window(allWindows.get(j));
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().maximize();
            Thread.sleep(1000);
        }

        if (checkPoint.getOperate_type() == null) { // Operate_type为空的话，默认就是find查找
            return status;
        } else if ((checkPoint.getOperate_type().equals("getValue") && checkPoint.getText() != null)) { //具体检查点
            try{
//            status = driver.findElement(by).getAttribute("value").equals(checkPoint.getText());
            status = driver.findElement(by).getText().equals(checkPoint.getText());
            Reporter.log("<b></br>CHECK："+status);
            Reporter.log("<b></br>检查点："+ checkPoint.getText()+","+"获取值："+driver.findElement(by).getText());
            }
            catch (Exception e){
                TakesScreenshot("error");
                Reporter.log("<b></br>ERROR：检查点失败<b></br>"+e);
                makeScreenshotOnFailure();
            }
        } else {
            status = false;
        }
        return status;
    }

    /***
     * 得到元素
     * @param find_type (id,xpath,name,classname)
     * @param element_info 具体元素信息
     * @return By
     */
    public By getElement(String find_type, String element_info){
        By by = null;
        if(find_type != null){
            switch (find_type) {
                case ID:
                    by = By.id(element_info);

                    break;
                case NAME:
                    by = By.name(element_info);

                    break;
                case XPATH:
                    by = By.xpath(element_info);

                    break;
                case CSSSELECTOR:
                    by = new By.ByCssSelector(element_info);
                    break;
                case CLASSNAME:
                    by = new By.ByClassName(element_info);
                    break;
                default:
                    log.info("不支持其他操作方法" + element_info);
                    break;
            }
        }
        return by;
    }

    public WebElement setElement(By by){

        WebElement webElement = this.driver.findElement(by);
        return webElement;

    }

    /***
     *  检查元素是否存在
     * @param elementLocator
     */
    public boolean waitForElement(final By elementLocator) {

        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            return true;
        }
        catch (NoSuchElementException | ElementNotVisibleException | org.openqa.selenium.TimeoutException e) {
            log.info("未知元素："+elementLocator);
            return false;
        }
    }

    /***
     *  清空文本框
     * webElement

     */
    public void clear(WebElement webElement) {
        webElement.clear();
    }

    /***
     *  步骤
     * TestCase 实体类
     * @param testCase
     */
    public boolean operate(TestCase testCase) throws InterruptedException {
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setFind_type(testCase.getFind_type());
        checkPoint.setElement_info(testCase.getElement_info());
        checkPoint.setStep(testCase.getStep());
        checkPoint.setFrame(testCase.getFrame());
        Reporter.log("<br></br><b>STEP："+testCase.getStep()+"</b>");
        if (testCase.getDefaultContent()!= null) { //切换到主页面
            Thread.sleep(1000);
            webBrower.defaultContent();
            log.info("----------------getDefaultContent--------------------");
            log.info("切换到主页面");
        }
        if(testCase.getWait_time()!= null){
            try{
                long s  = Long.valueOf(testCase.getWait_time());
                Reporter.log("<br>SLEEP："+testCase.getWait_time()+"ms");
                Thread.sleep(s);
            }
            catch (Exception e){
                Reporter.log("wait time error: "+e);
            }
        }
        if(testCase.getSwitch_window()!= null){
            Reporter.log("<br>切换至当前窗口");
            //获取当前窗口句柄
            String win = driver.getWindowHandle();
            //获取所有窗口句柄
            Set<String> Windows = driver.getWindowHandles();
            //把获取到的窗口句柄放到list中
            List<String> allWindows = new ArrayList<String>(Windows);
            int j = 0;
            for (int i = 0; i < allWindows.size(); i++) {
                if (!allWindows.get(i).equals(win)) {
                    j = i;
                }
            }
            driver.switchTo().window(allWindows.get(j));
            //切换到新打开的窗口并最大化
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().maximize();
            Thread.sleep(1000);
        }
        if(testCase.getElement_info() ==null){
            return false;
        }
        boolean check =  checkElement(checkPoint);
        if (check) {
            Thread.sleep(800);
            By by = getElement(testCase.getFind_type(), testCase.getElement_info());
            WebElement webElement = setElement(by);
            try {
                switch (testCase.getOperate_type()) {
                    case "click": { // 点击
                        webElement.click();
                        log.info("点击：" + testCase.getElement_info());
                        Reporter.log("<br>点击：" + testCase.getElement_info());
                        break;
                    }
                    case "send_keys": {  //输入内容
                        clear(webElement); // 清空文本框
                        webElement.sendKeys(testCase.getText());
                        log.info("在该元素下：" + testCase.getElement_info() + "<br></br>输入内容：" + testCase.getText());
                        Reporter.log("<br>在该元素下：" + testCase.getElement_info() + "<br>输入内容：" + testCase.getText());
                        break;
                    }
                    case "send_date_now": {  //输入内容
                        clear(webElement); // 清空文本框
                        Thread.sleep(1000);
                        webElement.sendKeys(testCase.getText()+dateNow);
                        log.info("在该元素下：" + testCase.getElement_info() + "<br></br>输入内容：" + testCase.getText()+dateNow);
                        Reporter.log("<br>在该元素下：" + testCase.getElement_info() + "<br>输入内容：" + testCase.getText()+dateNow);
                        break;
                    }
                    case "move_to":{ //移动鼠标
                        Actions action = new Actions(driver);
                        action.moveToElement(webElement).perform();
                        log.info("鼠标移动到："+ testCase.getElement_info());
                        break;
                    }
                    case "move_up":{ //按住向上拖动鼠标
                        Actions action = new Actions(driver);
                        action.dragAndDropBy(webElement,0,-150).perform();
                        log.info("按住向上拖动鼠标："+ testCase.getElement_info());
                        break;
                    }
                    case "move_hold":{ //按住向上拖动鼠标
                        Actions action = new Actions(driver);
                        action.release();
                        action.moveToElement(webElement, -100, -100).clickAndHold().moveByOffset(100,100).release().build().perform();
                        log.info("按住向上拖动鼠标："+ testCase.getElement_info());
                        break;
                    }
                    case "double_click":{ //对指定元素双击
                        Actions action = new Actions(driver);
                        action.doubleClick(webElement).perform();
                        log.info("对该元素双击："+ testCase.getElement_info());
                        break;
                    }
                    case "over_click":{
                        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
//                        WebDriver iframe = driver.switchTo().frame(0);
//                        jsexecutor.executeScript("arguments[0].click();",iframe.findElement(By.xpath("/html/body/div[10]/div/div/div[2]/button[1]"))); //点击确定
//                        jsexecutor.executeScript("arguments[0].click();",iframe.findElement(By.xpath(testCase.getElement_info()))); //点击确定
                        jsexecutor.executeScript("arguments[0].click();",webElement);
                        log.info("点击被覆盖的元素："+ testCase.getElement_info());
                        Reporter.log("点击被覆盖的元素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding":{
                        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
                        jsexecutor.executeScript("arguments[0].scrollIntoView(true);",webElement);
                        log.info("上下滑动使元素和窗口顶部对齐："+testCase.getElement_info());
                        Reporter.log("<br>上下滑动使元素和窗口顶部对齐："+ testCase.getElement_info());
                        break;
                    }
                    case "forSliding":{
                        ((JavascriptExecutor) driver).executeScript("document.getElementById('"+testCase.getElement_info()+"').setAttribute('style', ' top: -400px;position:relative;')\n");
                        log.info("指定元素向上滑动400像素："+testCase.getElement_info());
                        Reporter.log("<br>指定元素向上滑动400像素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding_up":{
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
                        log.info("向上滑动400像素："+testCase.getElement_info());
                        Reporter.log("<br>向上滑动400像素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding_up_800":{
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 800)");
                        log.info("向上滑动800像素："+testCase.getElement_info());
                        Reporter.log("<br>向上滑动800像素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding_down":{
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
                        log.info("向下滑动400像素："+testCase.getElement_info());
                        Reporter.log("<br>向下滑动400像素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding_left":{
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(400, 0)");
                        log.info("向左滑动400像素："+testCase.getElement_info());
                        Reporter.log("<br>向左滑动400像素："+ testCase.getElement_info());
                        break;
                    }
                    case "sliding_right":{
                        ((JavascriptExecutor) driver).executeScript("window.scrollBy(-400, 0)");
                        log.info("向右滑动400像素："+testCase.getElement_info());
                        Reporter.log("<br>向右滑动400像素："+ testCase.getElement_info());
                        break;
                    }
                    case "ENTER": { // 回车
                        webElement.sendKeys(Keys.ENTER);
                        log.info("回车：" + testCase.getElement_info());
                        break;
                    }
                    case "ESC": { // esc
                        webElement.sendKeys(Keys.ESCAPE);
                        log.info("ESC：" + testCase.getElement_info());
                        break;
                    }
                    case "display":{//显示隐藏元素
                        String Element_info = testCase.getElement_info();
                        ((JavascriptExecutor) driver).executeScript("document.querySelector('"+Element_info+"').style.display='block';");
                        log.info("显示隐藏的元素："+ testCase.getElement_info());
                        Reporter.log("显示隐藏的元素："+ testCase.getElement_info());
                        break;
                    }
                    default:
                        log.info("不支持操作方法：" + testCase.getOperate_type());
                        Reporter.log("<br>不支持操作方法：" + testCase.getOperate_type());
                        break;
                }
            }catch (Exception e){
                Reporter.log("<br>ERROR：" + e);
                TakesScreenshot("ERROR");
                makeScreenshotOnFailure();
            }
            if (testCase.getFrame()!= null) { // 切换到frame
                Thread.sleep(1000);
                webBrower.switchToFrame(testCase.getFrame());
                log.info("-------------getFrame--------------------");
                Reporter.log("<br>切换frame:"+testCase.getFrame());
            }
        }else {
            log.error("该元素不存在："+testCase.getElement_info());
            Reporter.log("<br>该元素不存在："+testCase.getElement_info());
        }
        return check;
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
//            this.driver.switchTo().defaultContent();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
            String dateNow = df.format(new Date());
            String fileName = screenPath + browserType + "_" + dateNow + ".png";
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            driver.switchTo().defaultContent();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(fileName));
            ExtentTestNGIReporterListener.fileName = fileName;
            String os = System.getProperty("os.name");
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

}
