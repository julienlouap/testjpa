package org.formation.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.formation.jsf.model.Student;


public class StudentDao implements IStudentDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");

	@Override
	public List<Student> getStudents() throws Exception {

		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		List<Student> listStudent = new ArrayList<Student>();

		try {
			txn.begin();

			TypedQuery<Student> query = em.createQuery("from Student", Student.class);
			listStudent = query.getResultList();

			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return listStudent;
	}

	@Override
	public void addStudent(Student theStudent) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {
			txn.begin();
			em.persist(theStudent);

			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Student getStudent(int studentId) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		Student stud = new Student();

		try {
			txn.begin();

			stud = (Student) em.find(Student.class, studentId);

			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return stud;
	}

	@Override
	public void updateStudent(Student theStudent) throws Exception {

		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {
			txn.begin();
			em.merge(theStudent);
			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void deleteStudent(int studentId) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		Student stud = new Student();

		try {
			txn.begin();

			stud = (Student) em.find(Student.class, studentId);
			em.remove(stud);

			txn.commit();

		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

}
