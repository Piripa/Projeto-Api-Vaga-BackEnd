package com.demo.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice 
public class ControllerException {
	
	/*
	 * 
	 * @param exception
	 * //Spring chama essa ultima classe pra ver se tem algum método que trata a exceção
	 * Tratamento de exceção
	 * @return
	 */
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity tentativadeEntradaDuplicada(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario ja cadastrado", "400");
		return ResponseEntity.badRequest().build();
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tentiva404(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity exceptionGerais(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(exceptionDTO);
		
	}
}
