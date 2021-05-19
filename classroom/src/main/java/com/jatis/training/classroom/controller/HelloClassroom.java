package com.jatis.training.classroom.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloClassroom {
	
	@Value("${server.port:0}")
	int serverPort;
	
	@GetMapping
	public String hello(@RequestHeader("X-Consumer-Username") String username) {
		return "Hello Classroom! "+username+", server port: "+serverPort;
	}
}
