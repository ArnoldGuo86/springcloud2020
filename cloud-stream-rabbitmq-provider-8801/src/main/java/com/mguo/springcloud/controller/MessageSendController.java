package com.mguo.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mguo.springcloud.service.IMessageProvider;

@RestController
public class MessageSendController {

	@Resource
	IMessageProvider messageProvider;

	@GetMapping("/sendMessage")
	public String sendMessage() {
		return messageProvider.send();
	}

}
