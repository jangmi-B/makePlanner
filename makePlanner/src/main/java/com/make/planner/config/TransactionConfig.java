package com.make.planner.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class TransactionConfig {
	
	/**
	 *  포인트 컷
	 */
	private static final String TX_POINTCUT_EXPRESSION = "execution(* com.make.planner.module..*Service.*(..))";
	/**
	 * 트랙잭션 메소드 타임아웃
	 */
	private static final int TX_METHOD_TIMEOUT = 30;
	
	/**
	 * DataSourceTransactionManager 설정
	 */
	@Primary
	@Bean(name = "txManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		log.info("[Bean] DataSourceTransactionManager");
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}

	/**
	 * 트랜잭션 인터셉터 설정
	 */
	@Bean
	public TransactionInterceptor txAdvice(DataSourceTransactionManager txManager) {
		log.info("[Bean] TransactionInterceptor");
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties txAttributes = new Properties();

		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));

		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);

		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();

		// write rollback-rule
		txAttributes.setProperty("insert*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("update*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("delete*", writeTransactionAttributesDefinition);
		txAttributes.setProperty("merge*", writeTransactionAttributesDefinition);
		// read-only
		txAttributes.setProperty("*", readOnlyTransactionAttributesDefinition);
		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(txManager);

		return txAdvice;
	}

	/**
	 * txAdivce PointCut Adivsor 설정
	 */
	@Bean
	public Advisor txAdviceAdvisor(@Qualifier("txManager") DataSourceTransactionManager txManager) {
		log.info("[Bean] Advisor");
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(TX_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice(txManager));
	}
	

}
