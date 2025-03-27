package com.compass.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	// Pode-se usar -> @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world!";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world!");
	}
	
	// A marcação @PathVariable serve para pegar o valor da variável, a qual será passada via URL
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldVariablePath(@PathVariable String name) {
		return new HelloWorldBean("Hello world, " + name + "!");
	}
	
}
