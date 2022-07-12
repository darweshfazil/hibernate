package com.adf.hibernate5;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCourseDemo 
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
    		
    		int theId = 1;
    		Instructor tempInstructor = (Instructor) session.get(Instructor.class, theId);
    		
    		System.out.println("Instructor: "+ tempInstructor);
    		
    		// get course for the instructor
    		System.out.println("Courses: "+ tempInstructor.getCourses());
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
