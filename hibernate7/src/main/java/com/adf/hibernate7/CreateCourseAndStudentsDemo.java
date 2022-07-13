package com.adf.hibernate7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo 
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
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
    	Session session = factory.getCurrentSession();
    	try {
    		
    		session.beginTransaction();
    		
    		// create a course
    		Course tempCourse = new Course("Pacman - scoere million points");
    		
    		System.out.println("\nSaving the course...");
    		session.save(tempCourse);
    		System.out.println("Saved the course: "+ tempCourse);
    		
    		// create the students
    		Student tempStudent1 = new Student("John", "Doe", "adf@adf.com");
    		Student tempStudent2 = new Student("Mary", "Public", "mary@adf.com");
    		
    		// add students to the course
    		tempCourse.addStudent(tempStudent1);
    		tempCourse.addStudent(tempStudent2);
    		
    		// save the students
    		System.out.println("\nSaving students...");
    		session.save(tempStudent1);
    		session.save(tempStudent2);
    		System.out.println("Saved students: "+ tempCourse.getStudents());
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
