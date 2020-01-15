package com.hepta.mercado.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.mercado.entity.EntidadeBase;

public class DAOGenerico<T extends EntidadeBase> {

	private static EntityManager manager = HibernateUtil.getEntityManager();

	public T findById(Class<T> clazz, Integer id) {
		return manager.find(clazz, id);
	}

	public void saveOrUpdate(T obj) {
		try {
			manager.getTransaction().begin();
			if (obj.getId() == null) {
				manager.persist(obj);
			} else {
				manager.merge(obj);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally {
			manager.close();
		}
	}

	public void remove(Class<T> clazz, Integer id) {
		T t = findById(clazz, id);
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}finally {
			manager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		List<T> t = new ArrayList<>();
		try {
			Query query = manager.createQuery("FROM Fabricante");
			t = query.getResultList();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return t;
	}
}
