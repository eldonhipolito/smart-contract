package com.poc.smart.contract.core;

import java.util.List;

public interface Transaction {

	void save();

	List<String> columns();

	String hashed();

	boolean matches(Transaction another);

}
