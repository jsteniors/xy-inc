package com.jstenio.xy_sinc.ws;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceModule implements Module{

	@Override
	public void configure(Binder binder) {
		// TODO Auto-generated method stub
		
	}
	
	public ObjectMapper getMapper() {
		SimpleModule module = new SimpleModule("simple-module", Version.unknownVersion());
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(module);
		return mapper;
	}

}
