package org.student.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_info")
@NoArgsConstructor
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Student Name can't be null")
	private String name;
	
	@NotNull(message = "Student's Father Name can't be null")
	private String fname;
	
	@NotNull(message = "Student's Mother Name can't be null")
	private String mname;
	
	@NotNull(message = "Phone No. can't be null")
	private String phone;
	
	@NotNull(message = "Student Address can't be null")
	private String address;
	
	@NotNull(message = "Student Class Name can't be null")
	private String sclass;
}
