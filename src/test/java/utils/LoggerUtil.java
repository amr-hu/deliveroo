package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class LoggerUtil {
    protected static ExtentTest test;
    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * This method is used to log an INFO log inside both the log txt file and the html report
     *
     * @param message the message to be logged
     */
    protected void logInfo(String message) {
        log.info(message);
        test.log(LogStatus.INFO, message);
    }

    /**
     * This method is used to log an ERROR log inside both the log txt file and the html report
     *
     * @param message the message to be logged
     */
    protected void logError(String message) {
        log.error(message);
        test.log(LogStatus.ERROR, message);
    }

    /**
     * This method is used to log an ERROR message with the thrown exception stack trace inside both the log txt file
     * and the html report
     *
     * @param message the message to be logged
     * @param e       the thrown error to log its stack trace
     */
    protected void logError(String message, Throwable e) {
        logError(String.format("%s<br><b>Stack Trace:</b> %s", message, Arrays.toString(e.getStackTrace())));
    }

    /**
     * This method is used to log an INFO log inside the log txt file and a PASS log inside the html report
     *
     * @param message the message to be logged
     */
    protected void logSuccess(String message) {
        log.info(message);
        test.log(LogStatus.PASS, message);
    }

    /**
     * This method is used to log an ERROR log inside the log txt file and a FAIL log inside the html report
     *
     * @param message the message to be logged
     */
    protected void logFail(String message) {
        log.error(message);
        test.log(LogStatus.FAIL, message);
    }

    /**
     * This method is used to log an INFO log inside the log txt file and a SKIP log inside the html report
     *
     * @param message the message to be logged
     */
    protected void logSkip(String message) {
        log.info(message);
        test.log(LogStatus.SKIP, message);
    }

    /**
     * This method is used to log an INFO log inside the log txt file and an UNKNOWN log inside the html report
     *
     * @param message the message to be logged
     */
    protected void logUnknown(String message) {
        log.info(message);
        test.log(LogStatus.UNKNOWN, message);
    }
}
