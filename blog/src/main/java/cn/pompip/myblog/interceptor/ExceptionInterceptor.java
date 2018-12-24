package cn.pompip.myblog.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
   public ModelAndView defaultErrorHandler(HttpServletRequest request , Exception e )  {
        ModelAndView mav =new ModelAndView();
        mav.addObject("errorCode", 500);
        mav.addObject("errorURL", request.getRequestURL());
        StringBuilder stackTrace =new StringBuilder(e.getMessage());
        stackTrace.append("\n\n");
        for (StackTraceElement element : e.getStackTrace()) {
            stackTrace.append(element).append("\n");
        }
        mav.addObject("errorStack", stackTrace.toString());
        mav.setViewName( "error");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes() ;
        logger.info("url={}", requestAttributes.getRequest().getRequestURI());
        return mav;
    }
}
