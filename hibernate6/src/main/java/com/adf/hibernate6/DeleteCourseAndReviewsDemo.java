package com.adf.hibernate6;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args ) throws HibernateException
    {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
    	Session session = factory.getCurrentSession();
    	try {
    		
    		session.beginTransaction();
    		
    		// get the course
    		int theId = 10;
    		Course tempCourse = (Course) session.get(Course.class, theId);
    		
    		// print the course
    		System.out.println("Deleting the course...");
    		System.out.println(tempCourse);
    		
    		// print the course reviews
    		System.out.println(tempCourse.getReviews());
    		
    		// delete the course
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
