package com.hepta.mercado.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import junit.framework.TestCase;

public class MercadoServiceTest extends TestCase {

	@Test
	public void testConexaoComServidor() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/mercado/rs");
		target.path("/produtos").request().get(String.class);
		assertTrue(Boolean.TRUE);
	}

}