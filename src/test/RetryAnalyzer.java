package test;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

    public class RetryAnalyzer implements IRetryAnalyzer {
        private int counter = 0;

        @Override
        public boolean retry(ITestResult result) {
            if(counter < 2)
            {
                counter++;
                return true;
            }
            return false;
        }
    }
