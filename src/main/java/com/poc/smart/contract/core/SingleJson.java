package com.poc.smart.contract.core;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SingleJson implements Jsonable {

	private String name;

	private Object value;

	public SingleJson(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String json() throws JsonProcessingException {

		return String.format("{\"%s\":%s}", this.name, this.value);
	}

}
