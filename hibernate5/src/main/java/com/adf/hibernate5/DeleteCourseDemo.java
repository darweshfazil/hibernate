package com.adf.hibernate5;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo 
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
    		
    		int theId = 11;
    		Course tempCourse = (Course) session.get(Course.class, theId);
    		
    		// delete course
    		System.out.println("Deleting the course: "+tempCourse);
    		session.delete(tempCourse);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
