package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import util.Retry;

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(Retry.class);
        }
    }
}