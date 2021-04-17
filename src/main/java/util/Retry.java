package util;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.log4j.Logger;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {
    public Logger log = Logger.getLogger(this.getClass().getName());
    private int retryCount = 1;
    private int maxRetryCount = 2;   // retry a failed test 1 additional times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            log.info("-----------------失败用例重跑第["+retryCount+"]次！-----------------");
            Reporter.log("<b><h2>-----------------失败用例重跑"+retryCount+"次！-----------------<br></br></h2></b>");
            retryCount++;
            return true;
        }
        return false;
    }
}
