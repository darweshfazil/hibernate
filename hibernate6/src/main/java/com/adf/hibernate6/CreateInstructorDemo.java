package com.adf.hibernate6;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws HibernateException
    {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
    	Session session = factory.getCurrentSession();
    	try {
    		
    		// create the objects
    		System.out.println("Creating new Instructor object....");
    		Instructor tempInstructor = new Instructor("Abdul", "Khadar", "abdul@adf.com");
    		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.abdul.com/youtube", "Video Games");
    		
    		//associate the objects
    		tempInstructor.setInstructorDetail(tempInstructorDetail);
    		
    		// start a transaction
    		session.beginTransaction();
    		
    		// save the instructor
    		//
    		// Note: this will ALSO save the details object
    		// because of CascadeType.ALL
    		//
    		System.out.println("Saving the instructor: "+tempInstructor);
    		session.save(tempInstructor);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		// add clean up code
    		session.close();
    		
    		factory.close();
    	}
    }
}
