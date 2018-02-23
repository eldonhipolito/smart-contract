package com.poc.smart.contract.core;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface HashStrategy {
	byte[] hash(CharSequence text) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
