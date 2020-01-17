package com.hepta.mercado.rest;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.entity.Produto;
import com.hepta.mercado.persistence.ProdutoDAO;

@RunWith(MockitoJUnitRunner.class)
class ProdutoServiceTest {
	
	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/mercado/rs/produtos";
	
	@InjectMocks
	private ProdutoService produtoService;
	
	@Mock
	private ProdutoDAO dao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("unused")
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient();
		service = client.target(UriBuilder.fromUri(URL_LOCAL).build());
	}
	
	@Test
	public void testListaTodosProdutos() {
		// QUANDO
		Response response = service.request().get();
		// ENTAO
		assertTrue(response.getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}
	
	@Test
	public void testInsereNovoProduto() throws Exception {
		// QUANDO
		ProdutoService produtoService = new ProdutoService();
		Produto novoProduto = new Produto();
		FabricanteService fabricanteService = new FabricanteService();
		Fabricante fabricante = new Fabricante();
		novoProduto.setNome("Mark 3");
		novoProduto.setEstoque(50);
		novoProduto.setUnidade("UND");
		novoProduto.setVolume(20.5);
		fabricante.setNome("Stark Industries");
		fabricanteService.fabricanteCreate(fabricante);
		novoProduto.setFabricante(fabricante);
		// ENTAO
		assertTrue(produtoService.produtoCreate(novoProduto).getStatusInfo().getStatusCode() == Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void testRemoveProduto() {
		// QUANDO
		ProdutoService produtoService = new ProdutoService();
		Produto removeProduto = new Produto();
		removeProduto.setId(20);
		// ENTAO
		assertTrue(produtoService.produtoDelete(removeProduto.getId()).getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

	@Test
	public void testAtualizaProduto() {
		// QUANDO
		ProdutoService produtoService = new ProdutoService();
		Produto atualizaProduto = new Produto();
		FabricanteService fabricanteService = new FabricanteService();
		Fabricante fabricante = new Fabricante();
		atualizaProduto.setId(21);
		atualizaProduto.setNome("Tenis");
		atualizaProduto.setEstoque(40);
		atualizaProduto.setUnidade("UND");
		atualizaProduto.setVolume(1.5);
		fabricante.setId(52);
		fabricante.getNome();
		fabricanteService.fabricanteCreate(fabricante);
		atualizaProduto.setFabricante(fabricante);
		// ENTAO
		assertTrue(produtoService.produtoUpdate(atualizaProduto).getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode());
	}

}
