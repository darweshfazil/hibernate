package com.adf.hibernate2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class DeleteDemo 
{
    public static void main( String[] args ) throws HibernateException
    {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	try {
    		
    		// start a transaction
    		session.beginTransaction();
    		
    		// get instructor by primary key/id
    		int theId = 1;
    		Instructor tempInstructor = (Instructor) session.get(Instructor.class, theId);
    		
    		// delete the instructors
    		if(tempInstructor != null) {
    			System.out.println("Found Instructor: "+ tempInstructor);
    			
    			System.out.println("Deleting: "+ tempInstructor);
    			
    			// Note: will also delete associated "details"
    			// because of CascadeType.ALL
    			//
    			session.delete(tempInstructor);
    			
    			// commit transaction
        		session.getTransaction().commit();
        		
        		System.out.println("Done!");
    		}
    		
    		else {
    			System.out.println("Instructor not found!!");
    			
    			System.out.println("Transaction Not Complete");
    		}
    	}
    	finally {
    		factory.close();
    	}
    }
}
