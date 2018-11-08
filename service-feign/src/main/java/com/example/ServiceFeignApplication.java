package com.example;

import com.example.filter.FeignFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}

	/**
	 * 配置过滤器
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new FeignFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("basicFilter");
		registration.setOrder(Integer.MAX_VALUE);
		return registration;
	}

	/**
	 * 创建一个bean
	 *
	 * @return
	 */
	@Bean(name = "basicFilter")
	public Filter sessionFilter() {
		return new FeignFilter();
	}
}
