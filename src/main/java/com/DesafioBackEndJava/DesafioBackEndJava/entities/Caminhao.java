package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Caminhao {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String placa;

	private String modelo;

	private int ano;

	@OneToOne(mappedBy = "caminhao")
	private Entrega entrega;
}
