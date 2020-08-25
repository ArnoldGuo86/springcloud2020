package com.mguo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class MessageReceiveListenerController {

	@Value("${server.port}")
	private String serverPort;

	@StreamListener(Sink.INPUT)
	public void inptut(Message<String> message) {
		System.out.println("PayLoad: " + message.getPayload() + " server port: " + serverPort);
	}
}
