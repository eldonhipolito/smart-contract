package com.poc.smart.contract;

import java.io.IOException;
import java.util.Properties;

import org.bouncycastle.util.encoders.Hex;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkConsumes;
import org.takes.facets.fork.TkFork;
import org.takes.facets.fork.TkMethods;
import org.takes.facets.fork.TkProduces;
import org.takes.http.Exit;
import org.takes.http.FtBasic;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.poc.smart.contract.core.DefaultTransactions;
import com.poc.smart.contract.core.IntegrityStore;
import com.poc.smart.contract.core.Transactions;
import com.poc.smart.contract.take.TkRawTransaction;
import com.poc.smart.contract.take.TkRetrieveTransaction;
import com.poc.smart.contract.take.TkVerifyTransaction;

public class Application {

	public static void main(String[] args) throws Exception {
		final String json = "application/json";
		final IntegrityStore is = loadContract();
		final Transactions trans = new DefaultTransactions(is);

		new FtBasic(
				new TkFork(
						new FkRegex("/save",
								new TkConsumes(new TkMethods(new TkProduces(new TkRawTransaction(is), json), "POST"),
										"application/x-www-form-urlencoded")),
						new FkRegex("/retrieve",
								new TkMethods(new TkProduces(new TkRetrieveTransaction(trans), json), "GET")),
						new FkRegex("/verify",
								new TkMethods(new TkProduces(new TkVerifyTransaction(trans, is), json), "GET"))),
				9999).start(Exit.NEVER);

	}

	private static IntegrityStore loadContract() throws IOException {

		Properties props = loadProps();

		ECKeyPair pair = ECKeyPair.create(Hex.decode(props.getProperty("application.key")));

		Credentials c = Credentials.create(pair);

		return IntegrityStore.load(props.getProperty("contract.address"), Web3j.build(new HttpService()),

				c, IntegrityStore.GAS_PRICE, IntegrityStore.GAS_LIMIT);
	}

	private static Properties loadProps() throws IOException {
		Properties props = new Properties();

		props.load(Application.class.getClassLoader().getResourceAsStream("application.properties"));

		return props;
	}

}
