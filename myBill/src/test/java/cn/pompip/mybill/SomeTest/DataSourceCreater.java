package cn.pompip.mybill.SomeTest;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceCreater {




    /**
     * 配置文件获取
     * @return
     * @throws IOException
     */
    public SqlSession getSqlSession() throws IOException {
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream("test.properties"));
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /**
     * mybatis 生成生成 datasource
     * @return
     */
    public DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/myblog_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        properties.setProperty("username", "chong");
        properties.setProperty("password", "314159");
        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();
        return dataSource;
    }

    /**
     * springboot 生成datasource;
     * @return
     */
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myblog_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSource.setUsername("chong");
        dataSource.setPassword("314159");
        return dataSource;
    }
}
