//package cn.pompip.myblog.interceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//@EnableWebSecurity
//public class SecurityInterceptor implements HandlerInterceptor {
//
//    @Override
//    public  boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler )throws Exception  {
//        if (request.getSession().getAttribute("user") == null) {
//            response.sendRedirect("/user/login");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request , HttpServletResponse response, Object handler , ModelAndView modelAndView)  {
//
//    }
//}
