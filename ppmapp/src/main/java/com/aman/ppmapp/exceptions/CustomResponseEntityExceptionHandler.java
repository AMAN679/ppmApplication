package com.aman.ppmapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex,WebRequest req)
	{
		ProjectIdExceptionResponse exceptionResponse=new ProjectIdExceptionResponse(ex.getMessage());
	   return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	
	}
	
	
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex,WebRequest req)
	{
		ProjectNotFoundExceptionResponse exceptionResponse=new ProjectNotFoundExceptionResponse(ex.getMessage());
	   return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UserNameAlreadyExistsException ex,WebRequest req)
	{
		UserNameAlreadyExistResponse exceptionResponse=new UserNameAlreadyExistResponse(ex.getMessage());
	   return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	
	}
	
	
	
}







