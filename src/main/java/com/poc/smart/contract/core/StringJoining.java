package com.poc.smart.contract.core;

import java.util.Collection;
import java.util.stream.Collectors;

public class StringJoining {

	private char delimiter;

	private Collection<String> collection;

	public StringJoining(Collection<String> collection, char delimiter) {
		this.collection = collection;
		this.delimiter = delimiter;
	}

	public String join() {
		return this.collection.stream().collect(Collectors.joining(delimiter + ""));
	}
}
