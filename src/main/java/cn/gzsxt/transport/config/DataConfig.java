package cn.gzsxt.transport.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfig {
	@Value(value="${db.driver}")
	private String driver;
	@Value(value="${db.url}")
	private String url;
	@Value(value="${db.username}")
	private String username;
	@Value(value="${db.password}")
	private String password;
	
	//1.获得数据源
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return  dataSource;
	}
	
	//2.获得会话工厂
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		factoryBean.setDataSource(this.getDataSource());
		try {
			//设置参数后，必须要填充
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//3.创建Mapper的动态对象，并且注入到容器里面
	@Bean
	public static MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer configurer=new MapperScannerConfigurer();
		//指定会话工厂
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		//指定映射接口
		configurer.setBasePackage("cn.gzsxt.transport.mapper");
		//指定扫描的注解
		configurer.setAnnotationClass(Mapper.class);
		return configurer;
	}
	
	//4.事务代理类
	@Bean
	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager dstm=new DataSourceTransactionManager();
		dstm.setDataSource(this.getDataSource());
		return dstm;
	}
	

}
