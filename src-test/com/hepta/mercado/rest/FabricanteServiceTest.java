package com.hepta.mercado.rest;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;

@RunWith(MockitoJUnitRunner.class)
class FabricanteServiceTest {

	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/mercado/rs/fabricantes";

	@InjectMocks
	private FabricanteService fabricanteService;
	
	@Mock
	private FabricanteDAO dao;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("unused")
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient();
		service = client.target(UriBuilder.fromUri(URL_LOCAL).build());
	}

	@Test
	void testListaTodosFabricantes() {
		// QUANDO
		Response response = service.request().get();
		// ENTAO
		assertTrue(response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

	@Test
	public void testInsereNovoFabricante() throws Exception {
		// QUANDO
		FabricanteService fabricanteService = new FabricanteService();
		Fabricante novoFabricante = new Fabricante();
		novoFabricante.setNome("Under Armour");
		// ENTAO
		assertTrue(fabricanteService.fabricanteCreate(novoFabricante).getStatusInfo().getStatusCode() == Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void testRemoveFabricante() {
		// QUANDO
		FabricanteService fabricanteService = new FabricanteService();
		Fabricante removeFabricante = new Fabricante();
		removeFabricante.setId(21);
		// ENTAO
		assertTrue(fabricanteService.fabricanteDelete(removeFabricante.getId()).getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

	@Test
	public void testAtualizaFabricante() {
		// QUANDO
		FabricanteService fabricanteService = new FabricanteService();
		Fabricante atualizaFabricante = new Fabricante();
		atualizaFabricante.setId(53);
		atualizaFabricante.setNome("Aerocool");
		// ENTAO
		assertTrue(fabricanteService.fabricanteUpdate(atualizaFabricante).getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

}
