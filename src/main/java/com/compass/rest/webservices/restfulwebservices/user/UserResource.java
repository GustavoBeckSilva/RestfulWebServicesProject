package com.compass.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUserById(@PathVariable int id){
		User user = service.findById(id);
		
		if(user == null)
			throw new UserNotFoundException("User id " + id + " does not exist");
		
		return service.findById(id);
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User newUser) {
		User user = service.addUser(newUser);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(user.getId())
						.toUri();
		
		/* 	Usar a parte do URI faz com que seja retornado a URL com o usuário criado
			Por exemplo: /users/4
		*/
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
	}

	
}
