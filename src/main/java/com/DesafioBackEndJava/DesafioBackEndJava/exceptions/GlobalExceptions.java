package com.DesafioBackEndJava.DesafioBackEndJava.exceptions;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.MensagemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptions extends RuntimeException {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<MensagemDTO> handleCustomException(CustomException ex) {
		MensagemDTO errorResponse = new MensagemDTO(ex.getMessage(), ex.getStatusCode());
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MensagemDTO> handleRuntimeException(RuntimeException ex) {
		MensagemDTO errorResponse = new MensagemDTO("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


