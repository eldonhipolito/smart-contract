package com.poc.smart.contract.core;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashStrategy implements HashStrategy {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		HashStrategy h = new SHA256HashStrategy();

		System.out.println(h.hash("Eldon, Test, Debet").length);
	}

	@Override
	public byte[] hash(CharSequence text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.toString().getBytes(StandardCharsets.UTF_8));
		return md.digest();
	}

}
