package com.sawmill.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryBeanTest", basePackages = 
						{ "com.sawmill.service" },transactionManagerRef="testTransactionManager")
public class DataSourceConfigurationTest {

	
	@Bean(name = "dataSourceTest")
	@Profile("test")
	@ConfigurationProperties(prefix = "spring.test.sawmill.datasource")
	public DataSource dataSourceTest() {
		return DataSourceBuilder.create().build();
	}

	
	@Bean(name = "entityManagerFactoryBeanTest")
	@Profile("test")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanTest(EntityManagerFactoryBuilder entityManagerFactoryBean,
			@Qualifier("dataSourceTest") DataSource dataSource) {

		return entityManagerFactoryBean.dataSource(dataSource).packages("com.sawmill.model").build();

	}

	
	@Bean(name = "testTransactionManager")
	@Profile("test")
	public PlatformTransactionManager transactionManagerTest(
			@Qualifier("entityManagerFactoryBeanTest") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
