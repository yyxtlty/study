package com.org.asean.javase.boot.thriftregist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@ComponentScan(basePackages= "com.org.asean.javase.boot.thriftregist")
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class
},scanBasePackages = {"com.ppdai.pointshop.web.controller"}
)
public class ThriftRegistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftRegistApplication.class, args);
	}
}
