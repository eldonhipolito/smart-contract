package com.poc.smart.contract.core;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bouncycastle.util.encoders.Hex;
import org.takes.rq.RqForm;
import org.takes.rq.form.RqFormSmart;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class RawTransaction implements Transaction {

	private String id;
	private char delimiter;
	private String columns;
	private String values;
	private HashStrategy hashStrategy;
	private IntegrityStore integrityStore;

	public RawTransaction(String id, char delimiter, String columns, String values, HashStrategy hashStrategy,
			IntegrityStore integrityStore) {
		this.id = id;
		this.delimiter = delimiter;
		this.columns = columns;
		this.values = values;
		this.hashStrategy = hashStrategy;
		this.integrityStore = integrityStore;
	}

	@Override
	public void save() {

		try {
			RemoteCall<TransactionReceipt> call = this.integrityStore.save(this.id,
					("" + delimiter).getBytes(StandardCharsets.UTF_8),
					new StringJoining(this.columns(), delimiter).join(), hash());
			call.send();
		} catch (Exception e) {
			throw new SystemException(e);
		}

	}

	@Override
	public List<String> columns() {
		String[] cols = colArray();
		Arrays.sort(cols);

		return Arrays.asList(cols);
	}

	private String[] colArray() {
		return this.columns.split(this.delimiter + "");
	}

	private String[] valArray() {
		return this.values.split(this.delimiter + "");
	}

	private byte[] hash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String[] cols = colArray();
		String[] vals = valArray();
		Map<String, String> mappedValues = new TreeMap<>();
		for (int ndx = 0; ndx < cols.length; ndx++) {
			mappedValues.put(cols[ndx], vals[ndx]);
		}
		return this.hashStrategy.hash(new StringJoining(mappedValues.values(), delimiter).join());

	}

	@Override
	public String hashed() {
		try {
			return new String(Hex.encode(hash()));
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public boolean matches(Transaction another) {
		return hashed().equalsIgnoreCase(another.hashed());
	}

}
