package com.ais.kafka.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ais.kafka.consumer.Receiver;
import com.ais.kafka.producer.Sender;

@Controller
@RequestMapping("/kafka")
public class SpringKafkaController {

	@Autowired
	private Sender sender;

	@Value("${kafka.topic.helloworld}")
	private String topic;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String sendMessage(
			@RequestParam(value = "message", required = false, defaultValue = "Hello from webservice") String message) {
		sender.send(topic, message);
		return "Message: " + message;
	}

	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getReceiveMessages() {
		ArrayList<String> receiveMessagesList = Receiver.receiveMessagesList;
		return new ResponseEntity<List<String>>(receiveMessagesList, HttpStatus.OK);
	}
}
