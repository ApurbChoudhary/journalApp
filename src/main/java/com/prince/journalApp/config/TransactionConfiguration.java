package com.prince.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

    @Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory databaseFactory) {
		return new MongoTransactionManager(databaseFactory);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
