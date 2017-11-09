package com.jstenio.xy_inc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("poisPU");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

}
