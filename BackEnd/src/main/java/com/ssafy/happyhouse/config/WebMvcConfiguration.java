package com.ssafy.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.annotations.ApiOperation;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration {

	
	//interceptor
	
}
