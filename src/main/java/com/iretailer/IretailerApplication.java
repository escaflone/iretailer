package com.iretailer;

import com.iretailer.Interceptor.AllowOriginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations={"classpath:spring/application.xml"})
@PropertySource(value = {"classpath:config/${spring.profiles.active}.properties"},ignoreResourceNotFound = false)
public class IretailerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(IretailerApplication.class, args);
	}
	/**
	 * 配置拦截器
	 * @author lance
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AllowOriginInterceptor()).addPathPatterns("/**");
	}
}