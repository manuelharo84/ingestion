package com.estadosdecuenta.ingestion;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

@SpringBootApplication
@RestController
public class IngestionApplication {

	private final static String TASK_QUEUE_NAME = "check";

	@RequestMapping("/")
	public String home() throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("rabbitmq");
		factory.setPort(5672);
		try (Connection connection = factory.newConnection();
				Channel channel = connection.createChannel()) {
			channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);

			for (int i = 1; i < 10; i++) {
				String message = "Hola Mundo" + i;
				channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes());
				System.out.println(" [x] Sent '" + message + "'");

			}
		}

		return "Hello Docker World";
	}

	public static void main(String[] args) {
		SpringApplication.run(IngestionApplication.class, args);

	}

}
