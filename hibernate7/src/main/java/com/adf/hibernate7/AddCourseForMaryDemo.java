package com.adf.hibernate7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourseForMaryDemo 
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
    		
    		// get the student mary from database
    		int theId = 2;
    		Student tempStudent = (Student) session.get(Student.class, theId);
    		
    		System.out.println("\nLoaded student: "+ tempStudent);
    		System.out.println("Courses: "+ tempStudent.getCourses());
    		
    		// create more courses
    		Course tempCourse1 = new Course("Rubik's Cube - solve it in seconds");
    		Course tempCourse2 = new Course("Atari 2600 - Game Development");
    		
    		// add student to courses
    		tempCourse1.addStudent(tempStudent);
    		tempCourse2.addStudent(tempStudent);
    		
    		// save the courses
    		System.out.println("\nSaving the courses...");
    		
    		session.save(tempCourse1);
    		session.save(tempCourse2);
    		
    		// commit transaction
    		session.getTransaction().commit();
    		
    		System.out.println("Done!");
    	}
    	finally {
    		
    		factory.close();
    	}
    }
}
