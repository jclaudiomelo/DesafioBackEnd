package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


public record EntregaDTO(
		@Schema(hidden = true)
		Long id,
		@Schema(example = "23.50")
		Double valor,
		@Schema(example = "Rua prereira n 23")
		String destino,
		@Schema(example = "2025-05-21")
		LocalDateTime horario,
		TipoCarga tipoCarga,
		boolean valiosa,
		boolean perigosa,
		boolean temSeguro,
		@Schema(example = "12")
		Long caminhaoId,
		@Schema(example = "1")
		Long motoristaId
) {
}
