//package cn.pompip.myblog.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import javax.sql.DataSource;
//
//@Configuration
//class DatasourceConfig {
//
//
//    @Bean("primaryDataSource")
//    @Primary
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.primary.datasource")
//    DataSource primaryDatasource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.secondary.datasource")
//    DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}
//
