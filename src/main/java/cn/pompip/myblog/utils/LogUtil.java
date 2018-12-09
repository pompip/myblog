package cn.pompip.myblog.utils;

import org.slf4j.LoggerFactory;

public class LogUtil {
    public static void loge(String msg) {
        LoggerFactory.getILoggerFactory().getLogger("LogUtil").error(msg);
    }
}
