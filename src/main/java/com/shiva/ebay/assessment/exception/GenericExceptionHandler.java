package com.shiva.ebay.assessment.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler {

	@ExceptionHandler(value = {NotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage resourceNotFoundException(NotFoundException ex, WebRequest request) {
		log.error("Not Found", ex);
		return ErrorMessage.builder().message(ex.getMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(e -> ErrorMessage.builder().message(e.getDefaultMessage()).build())
			.collect(Collectors.toList());
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ErrorMessage> bindException(BindException ex) {
		return ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(e -> ErrorMessage.builder().message(e.getDefaultMessage()).build())
				.collect(Collectors.toList());
	}
}
