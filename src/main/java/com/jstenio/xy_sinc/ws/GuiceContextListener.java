package com.jstenio.xy_sinc.ws;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.jstenio.xy_sinc.ws.dao.DAO;
import com.jstenio.xy_sinc.ws.model.POI;
import com.jstenio.xy_sinc.ws.resources.POIsResource;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModuleConfig(), new GuiceModule());
	}
	
	protected class ServletModuleConfig extends ServletModule{
		
		@Provides
		@Singleton
		public DAO<POI> getPoiDAO(EntityManager manager){
			return new DAO<POI>(POI.class, manager);
		}
		
		@Override
		protected void configureServlets() {
			String resources = POIsResource.class.getPackage().getName();
			JpaPersistModule module = new JpaPersistModule("poiPU");
			install(module);
			PackagesResourceConfig config = new PackagesResourceConfig(resources);
			for(Class<?> classe:config.getClasses()) {
				bind(classe);
			}
			filter("/*").through(PersistFilter.class);
			Map<String, String> prop = new HashMap<String, String>();
			prop.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
			
			serve("/resources/*").with(GuiceContainer.class, prop);
		}
	}

}
