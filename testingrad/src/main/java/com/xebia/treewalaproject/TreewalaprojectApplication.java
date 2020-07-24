package com.xebia.treewalaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.mail.MessagingException;

@SpringBootApplication
@EnableFeignClients
public class TreewalaprojectApplication {

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(TreewalaprojectApplication.class, args);
	}

}
