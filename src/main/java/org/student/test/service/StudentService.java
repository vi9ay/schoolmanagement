package org.student.test.service;

import java.util.List;

import org.student.test.entity.Student;

public interface StudentService {

	public Student addStudent(Student student);
	public List<Student> getAllStudents();
	public Student getStudent(long id);
	public void deleteStudentById(long id);
	public Student updateStudentById(Student student);
	
}
