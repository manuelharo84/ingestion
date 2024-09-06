package com.estadosdecuenta.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IngestionApplication { 

	public static void main(String[] args) throws Exception {
		SpringApplication.run(IngestionApplication.class, args);

			for (int i = 1; i < 100; i++) {
				String message = "Hola Mundo" + i;
				System.out.println(" [x] Sent '" + message + "'");

			}
	
	}

}
