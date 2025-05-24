package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;

import java.time.LocalDateTime;


public record EntregaDTO(
		Long id,
		Double valor,
		String destino,
		LocalDateTime horario,
		TipoCarga tipoCarga,
		boolean valiosa,
		boolean perigosa,
		boolean temSeguro,
		Long caminhaoId,
		Long motoristaId
) {
}
