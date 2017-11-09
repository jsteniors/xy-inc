package com.jstenio.xy_inc.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import com.jstenio.xy_inc.util.JPAUtil;
import com.jstenio.xy_inc.util.Parameter;
import com.jstenio.xy_inc.ws.dao.DAO;
import com.jstenio.xy_inc.ws.model.POI;

public class Teste {
	
	private final String BASE_URL = "http://localhost:8080/xy-inc/resources/pois";
	private DAO<POI> dao;
	private EntityManager manager = JPAUtil.getEntityManager();
	
	@Test
	public void testarConexao() {		
		POI p1 = new POI("Lanchonete", 27, 12);
		POI p2 = new POI("Posto", 31, 18);
		POI p3 = new POI("Joalheria", 15, 12);
		POI p4 = new POI("Floricultura", 19, 21);
		POI p5 = new POI("Pub", 12, 8);
		POI p6 = new POI("Supermercado", 23, 6);
		POI p7 = new POI("Churrascaria", 28, 2);
		
		manager.getTransaction().begin();
		List<POI> lista = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
		lista.forEach(a->createOrUpdate(a));		
		manager.getTransaction().commit();
		manager.close();
		
		String nome = "Lanchonete,Joalheria,Pub,Supermercado";
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		System.out.println("aq");
		List<POI> list = (List<POI>)target.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);
		
		String teste = list.stream().map(POI::getName).collect(Collectors.joining(","));
		
		Assert.assertEquals(nome, teste);	
	}
	
	public void createOrUpdate(POI poi) {
		System.out.println("sim");
		Parameter param = new Parameter("pPoi", poi);
		POI obj = manager.createNamedQuery("equalPoi", POI.class).setParameter("pPoi", poi).getSingleResult();
		
//				findByQuery("equalPoi", Arrays.asList(param));
		if(obj==null) {
			manager.persist(poi);
		}
		
	}
}
