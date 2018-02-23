package com.poc.smart.contract.take;

import java.io.IOException;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.form.RqFormSmart;
import org.takes.rs.RsFluent;

import com.poc.smart.contract.core.IntegrityStore;
import com.poc.smart.contract.core.RawTransaction;
import com.poc.smart.contract.core.SHA256HashStrategy;
import com.poc.smart.contract.core.SingleJson;
import com.poc.smart.contract.core.Transaction;
import com.poc.smart.contract.core.Transactions;

public class TkVerifyTransaction implements Take {

	private final Transactions transactions;

	private final IntegrityStore integrityStore;

	public TkVerifyTransaction(Transactions transactions, IntegrityStore integrityStore) {
		this.transactions = transactions;
		this.integrityStore = integrityStore;
	}

	@Override
	public Response act(Request req) throws IOException {
		RqFormSmart rqForm = new RqFormSmart(req);

		try {
			Transaction existingTran = transactions.retrieve(rqForm.single("id"));

			Transaction tranToVerify = new RawTransaction(rqForm.single("id"), rqForm.single("delimiter").charAt(0),
					rqForm.single("columns"), rqForm.single("values"), new SHA256HashStrategy(), integrityStore);

			return new RsFluent().withStatus(200).withType("application/json")
					.withBody(new SingleJson("matches", tranToVerify.matches(existingTran)).json());

		} catch (Exception e) {
			// TODO
		}

		return new RsFluent().withStatus(500).withType("application/json").withBody("Unable to process");
	}

}
