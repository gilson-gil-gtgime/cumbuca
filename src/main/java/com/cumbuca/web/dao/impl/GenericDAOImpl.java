package com.cumbuca.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cumbuca.api.singleton.EntityManagerFactorySingleton;
import com.cumbuca.web.dao.GenericDAO;

public class GenericDAOImpl<T,K> implements GenericDAO<T, K>{

	@PersistenceContext
	protected EntityManager em;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void cadastrar(T entidade) {
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
	}
	
	public void atualizar(T entidade) {
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}
	
	public T buscar(K chave) {
		return em.find(clazz, chave);
	}
	
	public void remover(K chave) throws Exception {
		T entidade = buscar(chave);
		if (entidade == null) {
			throw new Exception("Entidade não encontrada");
		}
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}
	
	public List<T> listar(){
		return em.createQuery("from " + clazz.getName(),clazz).getResultList();
	}
	
}
