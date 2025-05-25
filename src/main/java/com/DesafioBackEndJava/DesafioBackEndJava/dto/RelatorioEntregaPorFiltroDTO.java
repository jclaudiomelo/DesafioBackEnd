package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;

import java.time.LocalDateTime;

public record RelatorioEntregaPorFiltroDTO(
		Long entregaId,
		String placa,
		String destino,
		LocalDateTime horario,
		TipoCarga tipoCarga,
		Double valor
) {
}
