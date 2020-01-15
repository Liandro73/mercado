package com.hepta.mercado.persistence;

import java.util.List;

import com.hepta.mercado.entity.Produto;

public class ProdutoDAO {
	
	FabricanteDAO fabricanteDAO = new FabricanteDAO();

	DAOGenerico<Produto> dao = new DAOGenerico<Produto>();

	public void salvar(Produto produto) {
		fabricanteDAO.salvar(produto.getFabricante());
		dao.saveOrUpdate(produto);
	}

	public void delete(Integer id) {
		dao.remove(Produto.class, id);
	}

	public Produto find(Integer id) {
		return dao.findById(Produto.class, id);
	}

	public List<Produto> getAll() throws Exception {
		return dao.getAll();
	}

}