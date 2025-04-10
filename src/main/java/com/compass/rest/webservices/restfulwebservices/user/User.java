package com.compass.rest.webservices.restfulwebservices.user;



import java.time.LocalDate;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message = "Name should have at least 2 characters")
//	@JsonProperty("user_name") // Faz com que o nome retornado no JSON seja o especificado
	private String name;
	@Past(message = "Birthdate should be in the past")
//	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	public User() {}

	public User(Integer id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
