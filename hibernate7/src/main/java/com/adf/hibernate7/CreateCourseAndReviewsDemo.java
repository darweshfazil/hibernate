package com.adf.hibernate7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo 
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
    		
    		// create a course
    		Course tempCourse = new Course("Pacman - scoere million points");
    		
    		// add some reviews
    		tempCourse.addReview(new Review("Great course... loved it!"));
    		tempCourse.addReview(new Review("Good job"));
    		tempCourse.addReview(new Review("What a dumb course"));
    		
    		// save the course... and leverage the cascade all
    		System.out.println("Saving the course");
    		System.out.println(tempCourse);
    		System.out.println(tempCourse.getReviews());
    		
    		session.save(tempCourse);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
