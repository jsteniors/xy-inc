package com.jstenio.xy_inc.ws.resources;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jstenio.xy_inc.util.Parameter;
import com.jstenio.xy_inc.ws.dao.DAO;
import com.jstenio.xy_inc.ws.model.POI;

@Path("/pois")
@Produces(MediaType.APPLICATION_JSON)
public class POIsResource {
	
	private DAO<POI> dao;
	
	
	public POIsResource() {
		this.dao = new DAO<POI>(POI.class);
	}

	@GET
	public Response listAll(){
		return Response.ok(dao.listAll()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPOI(POI poi) {
		dao.insert(poi);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{x}/{y}/{d}")
	public Response getByDistance(@PathParam("x") Integer x, @PathParam("y") Integer y, @PathParam("d") Double d) {
		Parameter px = new Parameter("pX", x);
		Parameter py = new Parameter("pY", y);
		Parameter ds = new Parameter("pDistance", d);
		List<POI> pois = dao.listByQuery("poiByDistance", Arrays.asList(px, py, ds));
		return Response.ok(pois).build();
	}
}
