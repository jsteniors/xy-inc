package com.jstenio.xy_inc.ws.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.jstenio.xy_inc.util.JPAUtil;
import com.jstenio.xy_inc.util.Parameter;

public class DAO<T> {
	
	private Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public void insert(T entity){
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
    }
	
	public void insertMany(List<T> entities){
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        try {
        	Optional.ofNullable(entities).ifPresent(ent->ent.forEach(e->manager.persist(e)));
        }catch (Exception e) {
			return;
		}
        manager.getTransaction().commit();
        manager.close();
    }

    public List<T> listAll(){
        EntityManager manager = JPAUtil.getEntityManager();
        CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
        query.select(query.from(classe));
        List<T> result = manager.createQuery(query).getResultList();
        manager.close();
        return  result;
    }
   
    public void delete(T entity){
    	EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.merge(entity));
		manager.getTransaction().commit();
		manager.close();
    }
    
    public void update(T entity){
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
    }

    public T findbyId(Integer id){
        EntityManager manager = JPAUtil.getEntityManager();
        T result = manager.find(classe, id);
        manager.close();
        return result;
    }
    public List<T> listByQuery(String queryName,List<Parameter> params){
        EntityManager manager = JPAUtil.getEntityManager();
        TypedQuery<T> query = manager.createNamedQuery(queryName, classe);
        for(Parameter param:params){
        	if(!param.isStringConcat())
        		query.setParameter(param.getKey(), param.getValue());
        	else query.setParameter(param.getKey(), "%"+param.getValue()+"%");
        }
        List<T> result = query.getResultList();
        manager.close();
        return result;
    }
    
    public T findByQuery(String queryName, List<Parameter> params){
        try {
            EntityManager manager = JPAUtil.getEntityManager();
            TypedQuery<T> query = manager.createNamedQuery(queryName, classe).setMaxResults(1);
            for (Parameter param : params){
            	if(!param.isStringConcat())
            		query.setParameter(param.getKey(), param.getValue());
            	else query.setParameter(param.getKey(), "%"+param.getValue()+"%");
            	
            }
            T result = query.getSingleResult();
            manager.close();
            return result;
        }catch (NoResultException ex){
        	ex.printStackTrace();
            return null;
        }
    }

    
	
}
