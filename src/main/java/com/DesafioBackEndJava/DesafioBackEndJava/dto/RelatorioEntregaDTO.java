package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;

import java.time.LocalDateTime;

public record RelatorioEntregaDTO(
		Long caminhaoId,
		String motorista,
		String placa,
		LocalDateTime horarioEntrega,
		TipoCarga tipoCarga,
		Double valorEntrega
) {
}
