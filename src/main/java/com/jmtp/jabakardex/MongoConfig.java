package com.jmtp.jabakardex;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

public class MongoConfig {

	
	@Bean
	public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory,
			MongoMappingContext context, BeanFactory beanFactory) {
		context.setAutoIndexCreation(true);
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);

		// remove _class field
		mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

		try {
			mappingConverter
					.setCustomConversions(beanFactory.getBean(MongoCustomConversions.class));
		}
		catch (NoSuchBeanDefinitionException ex) {
			// Ignore
		}
		return mappingConverter;
	}
	
}
