package satu.spring.declare;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * @Author ISJINHAO
 * @Date 2022/2/27 14:03
 */
@EnableTransactionManagement
@PropertySource(value = {"classpath:db.properties"})
public class DeclareConfiguration implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setDriver((Driver) Class.forName(stringValueResolver.resolveStringValue("${db.driver}")).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        druidDataSource.setUrl(stringValueResolver.resolveStringValue("${db.url}"));
        druidDataSource.setUsername(stringValueResolver.resolveStringValue("${db.username}"));
        druidDataSource.setPassword(stringValueResolver.resolveStringValue("${db.password}"));
        druidDataSource.setMaxActive(1);
        druidDataSource.setInitialSize(1);
        return druidDataSource;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("satu.mybatis");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("TxTestMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }

}
