package cn.pompip.mybill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@MapperScan("cn.pompip.mybill.mappers")
public class MybillApplication {

    public static void main(String[] args){
        SpringApplication.run(MybillApplication.class,args);
    }
}
