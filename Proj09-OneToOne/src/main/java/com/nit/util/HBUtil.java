package com.nit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HBUtil {
	private static SessionFactory factory;

	static {
		try {
			//boot strap the HB f/w 
			Configuration cfg=new Configuration();
			cfg.configure("/com/nit/cfgs/hibernate.cfg.xml");
			//build session factory
			factory=cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	private HBUtil() {
		// No op
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static void closeSession(Session ses) {
		ses.close();
	}
	
	public static void closeSessionFactory() {
		factory.close();
	}
}
