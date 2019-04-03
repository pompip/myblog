package cn.pompip.myblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + "date" + "/**")
                .addResourceLocations("file:" + "D:/test" + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
