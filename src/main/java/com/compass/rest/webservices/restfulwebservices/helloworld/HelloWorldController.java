package com.compass.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello world!";
	}
	
}
