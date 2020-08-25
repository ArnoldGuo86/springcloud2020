package com.mguo.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Logger.Level;

@Configuration
public class FeignConfig {

	@Bean
	Logger.Level feignLogLevel(){
		return Level.FULL;
	}
}
