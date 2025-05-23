package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Motorista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@OneToMany(mappedBy = "motorista")
	private List<Entrega> entregas;

}
