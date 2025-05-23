package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaminhaoDTO {

	private Long id;
	private String placa;
	private String modelo;
	private int ano;

}
