package cn.pompip.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@MapperScan("cn.pompip.myblog.mappers")
public class MyBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(MyBlogApplication.class,args);
    }
}
