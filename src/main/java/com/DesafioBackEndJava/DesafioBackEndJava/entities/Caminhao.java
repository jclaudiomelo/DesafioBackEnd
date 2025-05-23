package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Caminhao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 10)
	private String placa;

	@Column(nullable = false, length = 100)
	private String modelo;

	@Column(nullable = false)
	private int ano;

	@OneToOne(mappedBy = "caminhao")
	private Entrega entrega;

}
