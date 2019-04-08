package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interfaces.DBManager;
import pojos.Course;
import pojos.Student;

public class JPAManager implements DBManager {

	private EntityManager em;

	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("students-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void insertCourse(Course c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertStudent(Student s, Course c) {
		// 1.- Persist Student
		em.getTransaction().begin();
		em.persist(s);
		// 2.- Link Student and Course
		s.addCourse(c);
		c.addStudent(s);
		em.getTransaction().commit();
	}

}
