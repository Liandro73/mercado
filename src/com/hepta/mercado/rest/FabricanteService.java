package com.hepta.mercado.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hepta.mercado.entity.Fabricante;
import com.hepta.mercado.persistence.FabricanteDAO;


@Path("/fabricantes")
public class FabricanteService {
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	private FabricanteDAO dao;
	
	public FabricanteService() {
		dao = new FabricanteDAO();
	}
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * Adiciona novo fabricante no mercado
	 * 
	 * @param produto: Novo produto
	 * @return response 200 (OK) - Conseguiu adicionar
	 */
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response fabricanteCreate(Fabricante fabricante) {
		try {
			dao.salvar(fabricante);
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity("Erro ao inserir fabricante").build();
		}
		
		return Response.status(Status.CREATED).entity("Conseguiu adicionar").build();
	}
	
	/**
	 * Lista todos os fabricantes cadastrados
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response fabricanteRead() {
		List<Fabricante> fabricantes = new ArrayList<>();
		try {
			fabricantes = dao.getAll();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar fabricantes").build();
		}
		
		GenericEntity<List<Fabricante>> entity = new GenericEntity<List<Fabricante>>(fabricantes) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * Atualiza um fabricante no mercado
	 * 
	 * @param id: id do fabricante
	 * @param produto: Fabricante atualizado
	 * @return response 200 (OK) - Conseguiu atualiza
	 */
	@Path("/atualiza {id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response fabricanteUpdate(@PathParam("id") Integer id, Fabricante fabricante) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}
	
	/**
	 * Remove um fabricante do mercado
	 * 
	 * @param id: id do fabricante
	 * @return response 200 (OK) - Conseguiu remover
	 */
	@Path("/remove {id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response fabricanteDelete(@PathParam("id") Integer id) {
		return Response.status(Status.NOT_IMPLEMENTED).build();
	}

}