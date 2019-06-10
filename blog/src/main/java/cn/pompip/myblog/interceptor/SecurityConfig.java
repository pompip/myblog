package cn.pompip.myblog.interceptor;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {



   public static class MyInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            LoggerFactory.getLogger(this.getClass()).info(request.getMethod());
            if (request.getMethod().equals("OPTIONS")){
                return true;
            }
            String token = request.getHeader("token");
            System.out.println(token);
            if (token==null){
                return false;
            }
            return true;
        }

    }


//    @Bean
    public MyInterceptor getMyInterceptor(){
       return new MyInterceptor();
    }
//
//    @Autowired
//    MyInterceptor myInterceptor;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()) .addPathPatterns("/**").excludePathPatterns("/user/**"); ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + "date" + "/**")
                .addResourceLocations("file:" + "D:/迅雷下载" + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
