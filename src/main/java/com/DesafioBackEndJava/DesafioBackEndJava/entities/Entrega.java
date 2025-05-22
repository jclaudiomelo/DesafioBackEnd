package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;
import jakarta.persistence.*;

@Entity
public class Entrega {


	@Id
	@GeneratedValue
	private Long id;

	private Double valor;
	private String destino;
	private String horario;

	@Enumerated(EnumType.STRING)
	private TipoCarga tipoCarga;

	private boolean valiosa;
	private boolean perigosa;
	private boolean temSeguro;

	@OneToOne
	private Caminhao caminhao;

	@ManyToOne
	private Motorista motorista;

}
