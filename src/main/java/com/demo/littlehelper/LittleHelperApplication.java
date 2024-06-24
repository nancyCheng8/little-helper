package com.demo.littlehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LittleHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(LittleHelperApplication.class, args);
	}

}
