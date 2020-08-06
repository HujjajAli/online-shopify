package net.haq.shopifybackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"net.haq.shopifybackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	private final String DATABASE_URL      = "jdbc:mysql://localhost:3306/onlineshopify";
	private final String DATABASE_DRIVER   = "com.mysql.jdbc.Driver";
	private final String DATABASE_DIALECT  = "org.hibernate.dialect.MySQLDialect";
	private final String DATABASE_USERNAME = "root";
	private final String DATABASE_PASSWORD = "";
	
	//Datasource Bean
	@Bean("dataSource")
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		
		//providing database connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	//SessionFactory Bean
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("net.haq.shopifybackend.dto");
		
		return builder.buildSessionFactory();
	}

	//Hibernate Properties
	public Properties getHibernateProperties() {
		Properties prop = new Properties();
		
		prop.put("hibernate.dialect", DATABASE_DIALECT);
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		
		return prop;
	}
	
	//Hibernate Transaction Manager Bean
	@Bean
	public HibernateTransactionManager getTransactionManger(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
