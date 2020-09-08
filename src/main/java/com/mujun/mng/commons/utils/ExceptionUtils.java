package com.mujun.mng.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    public ExceptionUtils() {
    }


    public static String getStackTrace(Throwable t) {
        if (t == null) {
            return null;
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            String var4;
            try {
                t.printStackTrace(pw);
                String stackStr = sw.toString();
                if (stackStr.length() <= 1000) {
                    var4 = stackStr;
                    return var4;
                }

                var4 = stackStr.substring(0, 1000);
            } finally {
                pw.close();
            }

            return var4;
        }
    }

    public static String getStackTraces(Throwable t) {
        if (t == null) {
            return null;
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            String var3;
            try {
                t.printStackTrace(pw);
                var3 = sw.toString();
            } finally {
                pw.close();
            }

            return var3;
        }
    }

    public static String getStackTrace(Throwable t, int length) {
        if (t == null) {
            return null;
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            String var5;
            try {
                t.printStackTrace(pw);
                String stackStr = sw.toString();
                if (stackStr.length() <= length) {
                    var5 = stackStr;
                    return var5;
                }

                var5 = stackStr.substring(0, length);
            } finally {
                pw.close();
            }

            return var5;
        }
    }

    public static String getStackTraceCausedBy(Throwable t, int length) {
        if (t == null) {
            return null;
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            String var5;
            try {
                t.printStackTrace(pw);
                String stackStr = sw.toString();
                if (stackStr.indexOf("Caused by") > -1) {
                    stackStr = stackStr.substring(stackStr.indexOf("Caused by"));
                }

                if (stackStr.length() > length) {
                    var5 = stackStr.substring(0, length);
                    return var5;
                }

                var5 = stackStr;
            } finally {
                pw.close();
            }

            return var5;
        }
    }

    public static String getRealErrorMsg(Throwable e) {
        String errorMsg = e.getMessage();
        if (errorMsg == null) {
            for(Throwable e1 = e; e1 != null; e1 = e1.getCause()) {
                if (e1.getMessage() != null) {
                    errorMsg = e1.getMessage();
                    break;
                }
            }
        }

        return errorMsg;
    }

    public static String getFeignErrorMsg(Throwable e) {
        StringBuilder sb = new StringBuilder(e.getMessage());
        Throwable e1 = e;

        for(int i = 0; e1 != null; ++i) {

            e1 = e1.getCause();
        }

        return sb.toString();
    }

}
