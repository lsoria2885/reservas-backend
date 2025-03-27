package com.mitocode.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mitocode.dto.GenericResponse;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse<CustomErrorResponse>> handleDefaultException(Exception ex,
			WebRequest request) {
		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(new GenericResponse<>(500, "error", List.of(errorResponse)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<GenericResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex,
			WebRequest request) {
		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(new GenericResponse<>(404, "bad-request", List.of(errorResponse)),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<CustomErrorResponse> handleArithmeticException(ArithmeticException ex, WebRequest request) {
		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.joining(","));
		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), message,
				request.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
