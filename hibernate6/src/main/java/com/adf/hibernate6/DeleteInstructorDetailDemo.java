package com.adf.hibernate6;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class DeleteInstructorDetailDemo 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws HibernateException
    {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	try {
    		
    		// start a transaction
    		session.beginTransaction();
    		
    		int theId = 8;
    		InstructorDetail tempInstructorDetail = (InstructorDetail) session.get(InstructorDetail.class, theId);
    		
    		System.out.println("tempInstructorDetail: "+tempInstructorDetail);
    		
    		System.out.println("the associated instructor: "+tempInstructorDetail.getInstructor());
    		
    		// now let's delete the instructor detail
    		System.out.println("Deleting tempInstructorDetail: "+ tempInstructorDetail);
    		
    		// remove the associated object reference
    		// break bi-directional link
    		tempInstructorDetail.getInstructor().setInstructorDetail(null);
    		
    		session.delete(tempInstructorDetail);
    		
    		// commit transaction
        	session.getTransaction().commit();
        	
        	System.out.println("Done!");
    	}
    	catch (Exception exc) {
    		exc.printStackTrace();
    	}
    	finally {
    		factory.close();
    	}
    }
}
