package com.jstenio.xy_inc.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

import com.jstenio.xy_inc.ws.dao.DAO;
import com.jstenio.xy_inc.ws.model.POI;

public class Teste {
	
	private final String BASE_URL = "http://localhost:8080/xy-inc/resources/pois";
	private DAO<POI> dao = new DAO<POI>(POI.class);
	
	@Test
	public void testarConexao() {
		
		POI p1 = new POI("Lanchonete", 27, 12);
		POI p2 = new POI("Posto", 31, 18);
		POI p3 = new POI("Joalheria", 15, 12);
		POI p4 = new POI("Floricultura", 19, 21);
		POI p5 = new POI("Pub", 12, 8);
		POI p6 = new POI("Supermercado", 23, 6);
		POI p7 = new POI("Churrascaria", 28, 2);
		
		
		List<POI> lista = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
				
		dao.insertMany(lista);
		
		Comparator<String> comparator = (a, b)->a.compareTo(b);
		
		List<String> nomes = Arrays.asList("Lanchonete","Joalheria","Pub","Supermercado");

		nomes.sort(comparator);
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);
		List<POI> list = target.path("20/10/10").request(MediaType.APPLICATION_JSON).get(new GenericType<List<POI>>() {});
		List<String> teste = list.stream().map(POI::getName).collect(Collectors.toList());
		teste.sort(comparator);
		System.out.println(teste);
		Assert.assertEquals(
			nomes.stream().collect(Collectors.joining(",")), 
			teste.stream().collect(Collectors.joining(","))
		);
		
	}
	
	
}
