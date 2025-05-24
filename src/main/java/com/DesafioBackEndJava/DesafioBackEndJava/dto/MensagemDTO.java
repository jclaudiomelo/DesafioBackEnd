package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MensagemDTO {
	private String message;
	private int statusCode;

	public MensagemDTO(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

}


