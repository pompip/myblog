package cn.pompip.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("cn.pompip.lib")
@EnableJpaRepositories(basePackages = "cn.pompip.lib.dao")
@SpringBootApplication
public class MyBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(MyBlogApplication.class,args);
    }
}
