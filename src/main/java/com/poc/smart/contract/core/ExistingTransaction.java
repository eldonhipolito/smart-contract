package com.poc.smart.contract.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.util.encoders.Hex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

public class ExistingTransaction implements Transaction, Jsonable {

	@SuppressWarnings("unused")
	private String id;

	private char delimiter;

	private String columns;

	private String hashed;

	@SuppressWarnings("unused")
	private long timestamp;

	public ExistingTransaction(String id, char delimiter, String columns, byte[] hashed, long timestamp) {
		this.id = id;

		this.delimiter = delimiter;

		this.columns = columns;

		this.hashed = new String(Hex.encode(hashed));

		this.timestamp = timestamp;
	}

	@Override
	public void save() {
		// DO nothing
	}

	@Override
	public List<String> columns() {
		return Arrays.asList(columns.split(this.delimiter + ""));
	}

	@Override
	public String hashed() {
		return this.hashed;
	}

	@Override
	public boolean matches(Transaction another) {
		return this.hashed().equalsIgnoreCase(another.hashed());
	}

	@Override
	public String json() throws JsonProcessingException {
		Map<String, String> fields = new HashMap<>();

		fields.put("id", this.id);
		fields.put("delimiter", this.delimiter + "");

		fields.put("columns", this.columns);

		fields.put("hashed", this.hashed());

		return new Gson().toJson(fields);
	}

}
