package cn.pompip.myblog.config;

import cn.pompip.myblog.utils.JsonUtil;
import cn.pompip.myblog.utils.LogUtil;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public LogUtil createLogUtil(){
        return new LogUtil();
    }

    @Bean
    public JsonUtil createJsonUtil(){
        return new JsonUtil();
    }


}
