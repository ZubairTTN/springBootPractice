package com.zubair.springBootPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
		System.out.println("Hello World");
	}

}
