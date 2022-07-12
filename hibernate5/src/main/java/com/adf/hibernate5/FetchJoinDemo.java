package com.adf.hibernate5;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchJoinDemo 
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
    		
    		session.beginTransaction();
    		
    		// option 2: Hibernate query with HQL
    		
    		int theId = 1;
    		org.hibernate.Query query = session.createQuery("select i from Instructor i "
    				+ "JOIN FETCH i.courses "
    				+ "where i.id=:theInstructorId");
    		
    		query.setParameter("theInstructorId", theId);
    		
    		Instructor tempInstructor = (Instructor)((Object) query).getSingleResult();
    		
    		System.out.println("adf: Instructor: "+ tempInstructor);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		// close the session
    		session.close();
    		
    		System.out.println("\nadf: The session is now closed!\n");
    		// option 1: call getter method while session is open
    		
    		// since courses are lazy loaded.. this should fail
    		
    		// get course for the instructor
    		System.out.println("adf: Courses: "+ tempInstructor.getCourses());
    		
    		System.out.println("adf: Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
