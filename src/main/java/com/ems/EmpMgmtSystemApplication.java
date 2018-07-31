package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AutoConfigurationPackage
@EnableJpaRepositories
@ComponentScan("com.ems.controller")
@ComponentScan("con.ems.service")
@EntityScan("com.ems.dao.entity")
@ComponentScan("com.ems.config")
public class EmpMgmtSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpMgmtSystemApplication.class, args);
	}
}
