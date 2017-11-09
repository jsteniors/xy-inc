package com.jstenio.xy_inc.ws;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig{
	public MyApplication() {
		packages("com.jstenio.xy_inc.ws.resources");
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		addProperties(props);
	}
}
