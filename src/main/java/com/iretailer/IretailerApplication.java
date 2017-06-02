package com.iretailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations={"classpath:spring/application.xml"})
@PropertySource(value = {"classpath:config/${spring.profiles.active}.properties"},ignoreResourceNotFound = false)
public class IretailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IretailerApplication.class, args);
	}
}
