package com.jstenio.xy_sinc.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.jstenio.xy_sinc.ws.dao.DAO;
import com.jstenio.xy_sinc.ws.model.POI;

@Path("/pois")
@Produces(MediaType.APPLICATION_JSON)
public class POIsResource {
	
	@Inject
	private DAO<POI> dao;
	
	@GET
	public List<POI> listAll(){
		return dao.listAll();
	}
}
