package com.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	private HibernateTemplate hibernateTemplate;

	// Save Student
	@Transactional
	public int insert(Student student) {
		// Insert
		Integer i = (Integer) this.hibernateTemplate.save(student);
		return i;
	}

	// Get the single data(Object)
	public Student getStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		return student;
	}

	// Get all students(all rows)
	public List<Student> getAllStudents() {
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		return students;
	}

	// Deleting the data
	@Transactional
	public void deleteStudent(int studentId) {
		Student student = this.hibernateTemplate.get(Student.class, studentId);
		this.hibernateTemplate.delete(student);
	}

	// Updating data
	@Transactional
	public void updateStudent(Student student) {
		this.hibernateTemplate.update(student);
	}
}
