package com.nit.test;

import com.nit.dao.OneToOneDao;
import com.nit.dao.OneToOneDaoImpl;

public class Test {

	public static void main(String[] args) {
		System.out.println("Test.main()");
		OneToOneDao dao=new OneToOneDaoImpl();
		
		//operations
		//dao.saveDataUsingStudent();
		//dao.fetchDataUsingStudent();
		dao.fetchDataUsingLib();
		
	}
}
