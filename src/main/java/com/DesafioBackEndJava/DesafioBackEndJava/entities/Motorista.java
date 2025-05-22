package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Motorista {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "motorista")
	private List<Entrega> entregas;
}
