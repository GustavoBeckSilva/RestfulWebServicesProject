package com.compass.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compass.rest.webservices.restfulwebservices.user.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository repository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	// EntityModel permite trabalhar com o HATEOAS
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("User id " + id + " does not exist");
		
		EntityModel<User> entityModel= EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// Permite adicionar link às inforações retornadas no get
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User newUser) {
		User user = repository.save(newUser);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(user.getId())
						.toUri();
		
		/* 	Usar a parte do URI faz com que seja retornado a URL com o usuário criado
			Por exemplo: /users/4
		*/
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}

	
}
