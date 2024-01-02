package org.student.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.student.test.entity.Student;
import org.student.test.exception.InvalidStudentIdException;
import org.student.test.repository.StudentRepository;
import org.student.test.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
@CrossOrigin(origins="http://localhost:4200/")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	public Student addNewStudentRecord(@Valid @RequestBody Student student)
	{
		return this.studentService.addStudent(student);
	}
	
	@GetMapping("/students")
	public List<Student> findAllStudents(){
		return this.studentService.getAllStudents();
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> findStudentById(@PathVariable long id) throws InvalidStudentIdException
	{
		if(this.studentRepository.findById(id).isPresent())
		{	
			return new ResponseEntity<>(this.studentService.getStudent(id),HttpStatus.OK);
		}
		else
		{
			
			//return new ResponseEntity<>("Student ID not found!",HttpStatus.NOT_FOUND);
			throw new InvalidStudentIdException("Student Id not found!");
			
		}
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id)
	{
		HashMap<String, String> response=new HashMap<>();

		if(this.studentRepository.findById(id).isPresent())
		{
			this.studentService.deleteStudentById(id);
			response.put("message", "Student deleted successfully!");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			response.put("message", "Student id not found!");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
//	@GetMapping("/students/name")
//	public ResponseEntity<?> getStudentsByName(@RequestParam("name") String name)
//	{
//		return new  ResponseEntity<>(this.studentRepository.findByName(name),HttpStatus.OK);
//	}
	@PatchMapping("/student/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable long id)
	{
		HashMap<String, String> response=new HashMap<>();
		if(this.studentRepository.findById(id).isPresent())
		{
			Student existingObj=this.studentRepository.findById(id).get();
			if(student.getName()!=null)
			{
				existingObj.setName(student.getName());
			}
			
			if(student.getFname()!=null)
			{
				existingObj.setFname(student.getFname());
			}
			
			if(student.getMname()!=null)
			{
				existingObj.setMname(student.getMname());
			}
			if(student.getPhone()!=null)
			{
				existingObj.setPhone(student.getPhone());
			}
			if(student.getAddress()!=null)
			{
				existingObj.setAddress(student.getAddress());
			}
			if(student.getSclass()!=null)
			{
				existingObj.setSclass(student.getSclass());
			}
			System.out.println(existingObj);
			this.studentService.updateStudentById(existingObj);
			response.put("message", "Student record updated!!");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			response.put("message", "Student id not found");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
}
