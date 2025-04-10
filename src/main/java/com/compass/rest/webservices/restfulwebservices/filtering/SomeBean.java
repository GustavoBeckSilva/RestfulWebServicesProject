package com.compass.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

// @JsonIgnoreProperties({"field1", "field2"}) // Não mostra os campos citados no JSON - Filtragem estática
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	
	//	@JsonIgnore // Não mostra o campo no JSON
	private String field1;
	private String field2;
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	
}
