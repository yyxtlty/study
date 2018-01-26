package com.org.asean.javase.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.xman.common.mybatis.SqlMonitorManager;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chaixinli on 2017/10/11.
 */
@Configuration
@MapperScan(basePackages = "com.org.asean.javase.web.dao")
public class MybatisConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Value("${datasource.driverClass}")
    private String jdbcDriver;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.jdbcUrl}")
    private String jdbcUrl;

    @Value("${datasource.maxIdle}")
    private String maxIdle;

    @Value("${datasource.minIdle}")
    private String minIdle;

    @Value("${datasource.maxWait}")
    private String maxWait;

    @Value("${datasource.validationQuery}")
    private String validationQuery;

    @Value("${datasource.maxActive}")
    private int maxActive;

    @Value("${datasource.testBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.timeBetweenEvictionRunsMills}")
    private long timeBetweenEvictionRunsMills;

    @Value("${datasource.minEvictableIdleTimeMillis}")
    private long minEvictableTimeMills;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.jdbcDriver);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setUrl(this.jdbcUrl);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setValidationQuery(this.validationQuery);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setTestOnReturn(this.testOnReturn);
        dataSource.setTestWhileIdle(this.testWhileIdle);
        dataSource.setTimeBetweenConnectErrorMillis(this.timeBetweenEvictionRunsMills);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableTimeMills);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.debug("start sqlSessionFactory");
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        sqlSessionFactory.setMapperLocations(getResource("mappers", "**/*.xml"));
        SqlMonitorManager sqlMonitorManager = new SqlMonitorManager();
        Properties properties = new Properties();
        properties.setProperty("show_sql", "true");
        sqlMonitorManager.setProperties(properties);

        /*PageInterceptor pageInterceptor = new PageInterceptor();
        Properties property = new Properties();
        properties.setProperty("databaseType", "mysql");
        pageInterceptor.setProperties(property);
        sqlSessionFactory.setPlugins(new Interceptor[]{sqlMonitorManager, pageInterceptor});*/
        return sqlSessionFactory.getObject();
    }

    private Resource[] getResource(String basePackage, String pattern) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(new StandardEnvironment()
                .resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        return resources;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        logger.debug("start transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }
}
