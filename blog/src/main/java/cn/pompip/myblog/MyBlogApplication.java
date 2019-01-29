package cn.pompip.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

//@EntityScan("cn.pompip.lib")
//@EnableJpaRepositories(basePackages = "cn.pompip.lib.dao")
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@MapperScan("cn.pompip.myblog.mapper")
public class MyBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(MyBlogApplication.class,args);
    }
}
