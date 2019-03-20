package cn.pompip.myblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {
    public static void loge(Object msg) {
        LoggerFactory.getILoggerFactory().getLogger("LogUtil").error(msg == null ? "null" : msg.toString());
    }

    public static void logo(Object msg) {
        String obj = null;
        try {
            obj = new ObjectMapper().writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        LoggerFactory.getILoggerFactory().getLogger("LogUtil").error(obj == null ? "null" : obj);
    }

    public void e(Object msg) {
        loge(msg);
    }
    @Autowired
    JsonUtil jsonUtil;

    public void object(Object msg){
        e(jsonUtil.toJson(msg));
    }
}
