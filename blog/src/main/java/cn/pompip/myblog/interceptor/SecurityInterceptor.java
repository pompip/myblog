package cn.pompip.myblog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public  boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler )throws Exception  {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/user/login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request , HttpServletResponse response, Object handler , ModelAndView modelAndView)  {

    }
}
