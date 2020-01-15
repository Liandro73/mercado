package com.hepta.mercado.persistence;

import java.util.List;

import com.hepta.mercado.entity.Fabricante;

public class FabricanteDAO {

	DAOGenerico<Fabricante> dao = new DAOGenerico<Fabricante>();

	public void salvar(Fabricante fabricante) {
		dao.saveOrUpdate(fabricante);
	}

	public void delete(Integer id) {
		dao.remove(Fabricante.class, id);
	}

	public Fabricante find(Integer id) {
		return dao.findById(Fabricante.class, id);
	}

	public List<Fabricante> getAll() throws Exception {
		return dao.getAll();
	}
	
}
