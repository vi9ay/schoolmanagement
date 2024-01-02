package org.student.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.student.test.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

//	public List<Student> findByName(String name);
//	public List<Student> findBySclass(String sclass);
	
}
