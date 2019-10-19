package cn.pompip.myblog.interceptor;

import cn.pompip.myblog.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@ControllerAdvice
public class ExceptionInterceptor  extends ResponseEntityExceptionHandler{
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorCode", 500);
        mav.addObject("errorURL", request.getRequestURL());
        StringBuilder stackTrace = new StringBuilder(e.getMessage());
        stackTrace.append("\n\n");
        for (StackTraceElement element : e.getStackTrace()) {
            stackTrace.append(element).append("\n");
        }
        mav.addObject("errorStack", stackTrace.toString());
        mav.setViewName("error");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        logger.info("url={}", requestAttributes.getRequest().getRequestURI());
        return mav;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ResponseModel model  = new ResponseModel();
        model.setCode(-2);
        model.setMsg("exception "+ex.toString()+" status:"+status.value());
        StackTraceElement[] stackTrace = ex.getStackTrace();
        Map<Integer,String> list = new TreeMap<>();
        for (StackTraceElement stackTraceElement: stackTrace){
            list.put(stackTraceElement.getLineNumber(),stackTraceElement.toString() );
        }
        model.setData(list);
        body= model;
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body, headers, status, request);

        return objectResponseEntity;
    }

}
