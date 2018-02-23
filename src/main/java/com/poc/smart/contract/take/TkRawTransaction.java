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
import com.poc.smart.contract.core.Transaction;

public class TkRawTransaction implements Take {

	private IntegrityStore integrityStore;

	public TkRawTransaction(IntegrityStore integrityStore) {
		this.integrityStore = integrityStore;
	}

	@Override
	public Response act(Request req) throws IOException {

		RqFormSmart rqForm = new RqFormSmart(req);
		try {
			Transaction tran = new RawTransaction(rqForm.single("id"), rqForm.single("delimiter").charAt(0),
					rqForm.single("columns"), rqForm.single("values"), new SHA256HashStrategy(), this.integrityStore);
			tran.save();
		} catch (Exception e) {
			return new RsFluent().withStatus(500).withType("application/json").withBody("Error performing request.");
		}
		return new RsFluent().withStatus(200).withType("application/json").withBody("Transaction sent to blockchain.");
	}

}
