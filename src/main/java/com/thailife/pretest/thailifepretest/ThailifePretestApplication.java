package com.thailife.pretest.thailifepretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.thailife.pretest.thailifepretest")
public class ThailifePretestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThailifePretestApplication.class, args);
	}

}
