package com.poc.smart.contract.core;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Jsonable {

	String json() throws JsonProcessingException;
}
