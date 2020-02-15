package com.nit.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nit.entity.LibMembership;
import com.nit.entity.Student;
import com.nit.util.HBUtil;

public class OneToOneDaoImpl implements OneToOneDao {
	private static Logger logger = Logger.getLogger(OneToOneDaoImpl.class);

	@Override
	public void fetchDataUsingLib() {
		logger.info("fetchDataUsingLib() method started");
		Session ses = null;
		Query query = null;
		List<LibMembership> list = null;
		try {
			// get session
			ses = HBUtil.getSession();
			logger.info("Session object created");

			// create query
			query = ses.createQuery("FROM LibMembership");

			// load objects
			list = query.getResultList();
			logger.info("LibMembership obj loaded");
			
			
			list.forEach(obj ->{
				System.out.println("LibMembership :: ");
				System.out.println(obj);
				System.out.println("Student :: ");
				System.out.println(obj.getStudent());
			});

			/*
			 * list.forEach(obj -> { System.out.println(obj.getId());
			 * System.out.println(obj.getJoiningDate());
			 * System.out.println("=====Child=====");
			 * System.out.println(obj.getStudent().getStudentId());
			 * System.out.println(obj.getStudent().getStudentName());
			 * System.out.println(obj.getStudent().getStudentAddress()); });
			 */

		} catch (Exception e) {
			logger.fatal("Exception raised : " + e.getMessage());
			e.printStackTrace();
		}
		HBUtil.closeSession(ses);
		logger.info("Session close");
		logger.info("fetchDataUsingLib() method ended");
	}

	@Override
	public void fetchDataUsingStudent() {
		logger.info("fetchDataUsingStudent() method started");
		Session ses = null;
		Query<Student> query = null;
		List<Student> list = null;
		LibMembership lib = null;
		try {
			// get session
			ses = HBUtil.getSession();
			logger.info("Session object created");

			// create query
			query = ses.createQuery("FROM Student");

			// load objects
			list = query.getResultList();
			logger.info("Student obj loaded");

			for (Student student : list) {
				System.out.println("=====Parent======");
				System.out.println(student.getStudentId());
				System.out.println(student.getStudentName());
				System.out.println(student.getStudentAddress());
				System.out.println("=====Child=====");
				lib = student.getMember();
				System.out.println(lib.getId());
				System.out.println(lib.getJoiningDate());
			}
		} catch (Exception e) {
			logger.fatal("Exception raised : " + e.getMessage());
			e.printStackTrace();
		}
		HBUtil.closeSession(ses);
		logger.info("Session close");
		logger.info("fetchDataUsingStudent() method ended");
	}

	@Override
	public void saveDataUsingStudent() {
		logger.info("saveDataUsingStudent() method started");
		Session ses = null;
		Transaction tx = null;
		Student student = null;
		LibMembership lib = null;
		try {
			// get session
			ses = HBUtil.getSession();
			logger.info("Session object created");

			// create objects
			student = new Student();
			student.setStudentName("RAM");
			student.setStudentAddress("MIRAJ");

			lib = new LibMembership();
			lib.setJoiningDate(new Date());
			// set student to lib
			lib.setStudent(student);
			// set lib to student
			student.setMember(lib);

			// begin Transaction
			tx = ses.beginTransaction();
			logger.info("Transaction started");

			// save objects
			ses.save(student);
			logger.debug("Objects saved");
			// commit the Transaction
			tx.commit();
		} catch (Exception e) {
			logger.fatal("Exception raised : " + e.getMessage());
			e.printStackTrace();
			// rollback the Transaction
			tx.rollback();
		}
		logger.info("Session close");
		HBUtil.closeSession(ses);
		logger.info("saveDataUsingStudent() method ended");
	}
}
