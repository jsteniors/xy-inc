package com.jstenio.xy_sinc.ws.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	
	private Class<T> classe;
	private EntityManager manager;
	
	public DAO(Class<T> classe, EntityManager manager) {
		this.classe = classe;
		this.manager = manager;
		
		if(manager!=null) {
			System.out.println("aberto: "+manager.isOpen());
			System.out.println("ativo: "+manager.getTransaction().isActive());
		}else System.out.println("manager null");
	}
	
	public List<T> listAll(){
		
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> result = manager.createQuery(query).getResultList();
		
		return result;
	}
	
	public void delete(T entity){
		manager.remove(manager.merge(entity));
    }
    
    public void update(T entity){
        manager.merge(entity);
    }

    public T findbyId(Integer id){
        T result = manager.find(classe, id);
        return result;
    }

    
	
}
