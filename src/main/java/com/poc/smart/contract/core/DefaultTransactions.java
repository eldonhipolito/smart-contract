package com.poc.smart.contract.core;

import java.math.BigInteger;

import org.web3j.tuples.generated.Tuple6;

public class DefaultTransactions implements Transactions {

	private IntegrityStore integrityStore;

	public DefaultTransactions(IntegrityStore integrityStore) {
		this.integrityStore = integrityStore;
	}

	@Override
	public ExistingTransaction retrieve(String id) throws Exception {

		Tuple6<String, byte[], String, byte[], BigInteger, Boolean> result = integrityStore.singleTransaction(id)
				.send();
		// not existing record
		if (!result.getValue6().booleanValue()) {
			throw new RecordNotExistingException();
		}
		return new ExistingTransaction(result.getValue1(), (char) result.getValue2()[0], result.getValue3(),
				result.getValue4(), result.getValue5().longValue());

	}

}
