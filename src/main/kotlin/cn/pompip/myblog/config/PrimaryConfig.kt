package cn.pompip.myblog.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManager
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackageClasses = [])
class PrimaryConfig {
    @Autowired
    @Qualifier("primaryDataSource")
    lateinit var primaryDataSource: DataSource;

    @Primary
    @Bean(name = ["entityManagerPrimary"])
    fun entityManager(builder: EntityManagerFactoryBuilder): EntityManager {
        return entityManagerFactoryPrimary(builder).getObject()?.createEntityManager()!!;
    }

    @Primary
    @Bean(name = ["entityManagerFactoryPrimary"])
    fun entityManagerFactoryPrimary(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.zxl.examples.entity") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    lateinit var jpaProperties: JpaProperties;

    fun getVendorProperties(dataSource: DataSource): Map <String,String>{
        return jpaProperties.properties
    }

    @Primary
    @Bean(name = ["transactionManagerPrimary"])
    fun transactionManagerPrimary(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject()!!);
    }

}