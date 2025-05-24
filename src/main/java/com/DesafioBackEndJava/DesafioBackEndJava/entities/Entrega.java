package com.DesafioBackEndJava.DesafioBackEndJava.entities;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double valor;
	private String destino;
	private LocalDateTime horario;

	@Enumerated(EnumType.STRING)
	private TipoCarga tipoCarga;

	private boolean valiosa;
	private boolean perigosa;
	private boolean temSeguro;

	@OneToOne
	@JoinColumn(name = "caminhao_id")
	private Caminhao caminhao;

	@ManyToOne
	private Motorista motorista;

}
