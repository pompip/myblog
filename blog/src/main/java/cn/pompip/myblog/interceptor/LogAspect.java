package cn.pompip.myblog.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

//@Aspect
//@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * cn.pompip.myblog.control..*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore() throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder builder = new StringBuilder();
        builder.append("headers:").append("\n");
        Collections.list(request.getHeaderNames()).forEach((name)->{
            builder.append(name).append(" : ").append(request.getHeader(name)).append("\n");
        });
        builder.append("http:").append("\n");
        builder.append("url:").append(request.getRequestURI()).append("\n");
        builder.append("method:").append(request.getMethod()).append("\n");
        request.getParameterMap().forEach(( key,value)->{
            builder.append("param:").append(key).append(":").append(value.length>0?value[0]:"null").append("\n");
        });
        builder.append("reader:");

//        request.getReader().lines().forEach((line)->{
//            builder.append(line);
//        });
        builder.append("\n").append("class:").append(request);
        logger.info(builder.toString());

    }

    @After("log()")
    public void doAfter() {

    }
}
