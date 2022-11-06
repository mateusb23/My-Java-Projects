package entities.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Student;

public class StudentController {

	EntityManagerFactory emf;
	EntityManager em;
	
	public StudentController() {
		emf = Persistence.createEntityManagerFactory("uni-jpa");
		em = emf.createEntityManager();
	}
	
	public void add(Student student) {
		em.getTransaction().begin();
		em.merge(student);
		em.getTransaction().commit();
		emf.close();
	}
	
	public void delete(Student student) {
		em.getTransaction().begin();
		Query q = em.createNativeQuery("DELETE tb_student FROM tb_student WHERE register = " + student.getRegister());
		q.executeUpdate();
		em.getTransaction().commit();
		emf.close();
	}
	
}
