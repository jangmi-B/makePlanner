package com.make.planner.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages= "com.make.planner.**.dao")
public class DatasourceConfig {
	
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource") 
	public DataSource DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(DataSource());
		sessionFactory.setConfiguration(myBatisConfig());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
		
//		sessionFactory.setTypeAliasesPackage("com.create.something.**.model");
        return sessionFactory.getObject();
	}
	
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
    }
	
	@Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	private org.apache.ibatis.session.Configuration myBatisConfig() {
		org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
		// DB컬럼과 모델 프라퍼티를 자동 맵핑하기 위한 설정 (언더바를 카멜표기법에 맞게 변환)
		mybatisConfig.setMapUnderscoreToCamelCase(true);
		// NULL 허용 설정
		mybatisConfig.setJdbcTypeForNull(JdbcType.VARCHAR);

		return mybatisConfig;
	}

}
