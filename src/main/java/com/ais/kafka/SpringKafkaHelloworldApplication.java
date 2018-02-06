package com.ais.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ais.kafka.producer.Sender;

@SpringBootApplication
public class SpringKafkaHelloworldApplication implements CommandLineRunner {

	@Autowired
	private Sender sender;

	@Value("${kafka.topic.helloworld}")
	private String topic;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaHelloworldApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		sender.send(topic, "Hello spring kafka");

	}
}
