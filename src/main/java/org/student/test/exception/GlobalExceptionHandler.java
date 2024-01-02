package org.student.test.exception;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArugumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		List<FieldError> errors=ex.getBindingResult().getFieldErrors();
		
		HashMap<String, String> response=new HashMap<>();
		errors.stream().forEach(error -> {
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> methodArugumentNotValidExceptionHandler(MethodArgumentNotValidException ex )
//	{
//		List<FieldError> errors=ex.getBindingResult().getFieldErrors();
//		
//		HashMap<String, String> response=new HashMap<>();
//		
//		errors.stream().forEach(error -> {
//			String fieldName=((FieldError)error).getField();
//			String message=error.getDefaultMessage();
//			response.put(fieldName, message);
//		});
//		
//		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
//		
//	}
	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> IllegalArgumentExceptionHandler(IllegalArgumentException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> NullPointerExceptionHandler(NullPointerException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(InvalidStudentIdException.class)
	public ResponseEntity<?> InvalidStudentIdExceptionHandler(InvalidStudentIdException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> genericExceptionHandler(Exception ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		 
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
