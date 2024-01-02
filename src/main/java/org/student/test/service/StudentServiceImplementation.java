package org.student.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.student.test.entity.Student;
import org.student.test.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student addStudent(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		
		return this.studentRepository.findAll();
	}

	@Override
	public Student getStudent(long id) {
		
		return this.studentRepository.findById(id).get();
	}

	@Override
	public void deleteStudentById(long id) {
		this.studentRepository.deleteById(id);
		
	}

	@Override
	public Student updateStudentById(Student student) {
		return this.studentRepository.save(student);
		
	}
}
