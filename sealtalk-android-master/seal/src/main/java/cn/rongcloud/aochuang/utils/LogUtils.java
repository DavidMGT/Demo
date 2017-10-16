package cn.rongcloud.aochuang.utils;

import android.util.Log;

/**
 * Created by t00284576 on 2015/10/27.
 */
public class LogUtils {
    private final static String TAG = "aochuang";
    static String className;
    static String methodName;
    static int lineNumber;

    /**
     * 日志控制：发布版本不需要打印日志 当CURRENT = VERBOSE时所有日志都打印 当CURRENT = NOTHING时 所有日志都不打印
     * zwx220990
     */
    private static int VERBOSE = 0;
    private static int DEBUG = 4;
    private static int INFO = 2;
    private static int WARNING = 3;
    private static int ERROR = 4;
    private static int NOTHING = 5;
    private static int CURRENT = isDebuggable() ? VERBOSE : WARNING;

    public static boolean isDebuggable() {
        return true;
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message) {

        if (ERROR >= CURRENT) {
            if (!isDebuggable()) {
                Log.e(TAG, message + "");
                return;
            }
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(message));
        }
    }

    public static void i(String message) {
        if (INFO >= CURRENT) {
            if (!isDebuggable()) {
                Log.i(TAG, message + "");
                return;
            }
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(message));
        }

    }

    public static void d(String message) {
        if (DEBUG >= CURRENT) {
            if (!isDebuggable()) {
                Log.d(TAG, message + "");
                return;
            }
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className + TAG, createLog(message));
        }
    }

    public static void v(String message) {
        if (VERBOSE >= CURRENT) {
            if (!isDebuggable()) {
                Log.v(TAG, message + "");
                return;
            }

            getMethodNames(new Throwable().getStackTrace());
            Log.v(className, createLog(message));
        }
    }

    public static void w(String message) {
        if (WARNING >= CURRENT) {
            if (!isDebuggable()) {
                Log.w(TAG, message + "");
                return;
            }

            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(message));
        }
    }

}
