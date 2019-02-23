package cn.pompip.myblog.utils;

import org.slf4j.LoggerFactory;

public class LogUtil {
    public static void loge(Object msg) {
        LoggerFactory.getILoggerFactory().getLogger("LogUtil").error(msg==null?"null":msg.toString());
    }
}
