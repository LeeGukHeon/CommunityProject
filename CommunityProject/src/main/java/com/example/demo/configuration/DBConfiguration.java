package com.example.demo.configuration;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {
	// 순서 : db연결설정 - 데이터소스 - 데이터소스를 이용해서 sql sessionfactory 만들기
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {//db연결설정
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() {// 데이터소스 설정
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		//위치 가져오기위해서 applicationContext 사용
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		factoryBean.setTypeAliasesPackage("com.example.demo.mapper");
		factoryBean.setConfiguration(mybatisConfig()); //MyBatis Configuration 추가
		return factoryBean.getObject();
 	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
