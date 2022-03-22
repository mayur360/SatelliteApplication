package com.satellite.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({ "com.satellite.config", "com.satellite.config", "com.satellite.reader", "com.satellite.processor",
		"com.satellite.writter", "com.satellite.service", "com.satellite.listener" })
@EnableScheduling
public class SatelliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatelliteApplication.class, args);
	}

}
