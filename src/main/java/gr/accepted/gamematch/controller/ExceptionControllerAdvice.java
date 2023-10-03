package gr.accepted.gamematch.controller;

import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gr.accepted.gamematch.exception.CustomException;
import gr.accepted.gamematch.exception.ExceptionData;
import gr.accepted.gamematch.exception.InternalServerError;
import gr.accepted.gamematch.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@RestController
@Log4j2
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionData handleNotFoundException(NotFoundException ex, WebRequest request) {

		return ex.getExceptionData();
	}

	@ExceptionHandler(InternalServerError.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionData handleInternalServerError(InternalServerError ex, WebRequest request) {

		return ex.getExceptionData();
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String errors = ex.getBindingResult().getAllErrors().stream()
				.map(error -> String.format("%s %s", ((FieldError) error).getField(), error.getDefaultMessage()))
				.collect(Collectors.joining(", "));

		ExceptionData exceptionData = new ExceptionData();
		exceptionData.setMessage(errors);
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(exceptionData, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ExceptionData exceptionData = new ExceptionData();
		exceptionData.setMessage(ex.getMessage());
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(exceptionData, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionData handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		String errors = ex.getConstraintViolations().stream()
				.map(error -> String.format("%s %s", error.getPropertyPath(), error.getMessage()))
				.collect(Collectors.joining(", "));

		ExceptionData exceptionData = new ExceptionData();
		exceptionData.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		exceptionData.setCode(CustomException.CODE_ERROR);
		exceptionData.setMessage(errors);

		log.error("Request: {}, {}", ((ServletWebRequest) request).getRequest().getRequestURI(), errors);

		return exceptionData;
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		ExceptionData exceptionData = new ExceptionData();
		exceptionData.setMessage(ex.getMessage());
		exceptionData.setHttpStatus(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(exceptionData, HttpStatus.BAD_REQUEST);
	}

}
