package com.zzq.FourSubject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zzq.FourSubject.dao")
public class FourSubjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FourSubjectApplication.class, args);
	}

}
