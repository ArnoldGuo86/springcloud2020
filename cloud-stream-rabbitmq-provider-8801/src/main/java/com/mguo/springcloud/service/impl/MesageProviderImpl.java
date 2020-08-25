package com.mguo.springcloud.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.mguo.springcloud.service.IMessageProvider;

@EnableBinding(Source.class)
public class MesageProviderImpl implements IMessageProvider {

	@Resource(name = "output")
	private MessageChannel outputChannel;

	@Override
	public String send() {

		String content = UUID.randomUUID().toString();

		Message<String> message = MessageBuilder.withPayload(content).build();
		outputChannel.send(message);

		System.out.println("====message: ====" + content);
		return null;
	}
}
