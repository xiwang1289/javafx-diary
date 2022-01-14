package org.coffee.diary.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@EnableConfigurationProperties(DiaryProperties.class)
public class DiaryAutoConfiguration {

	@Autowired
	private DiaryProperties diaryProperties;

	@Bean
	public Gson gson(GsonBuilder gsonBuilder) {

		return gsonBuilder.create();
	}
}
