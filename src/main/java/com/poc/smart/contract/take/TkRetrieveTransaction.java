package com.poc.smart.contract.take;

import java.io.IOException;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqHref;
import org.takes.rs.RsFluent;
import org.takes.rs.RsWithBody;
import org.takes.rs.RsWithStatus;

import com.poc.smart.contract.core.Jsonable;
import com.poc.smart.contract.core.RecordNotExistingException;
import com.poc.smart.contract.core.Transactions;

public class TkRetrieveTransaction implements Take {

	private Transactions transactions;

	public TkRetrieveTransaction(Transactions transactions) {
		this.transactions = transactions;
	}

	@Override
	public Response act(Request req) throws IOException {
		try {
			Jsonable json = this.transactions.retrieve(new RqHref.Smart(req).single("id"));
			return new RsFluent().withBody(json.json()).withStatus(200).withType("application/json");
		} catch (RecordNotExistingException e) {
			return new RsFluent(new RsWithStatus(new RsWithBody("Record not found"), 404)).withType("application/json");
		} catch (Exception e) {
			return new RsFluent(new RsWithStatus(new RsWithBody("Unable to process"), 404))
					.withType("application/json");

		}

	}

}
