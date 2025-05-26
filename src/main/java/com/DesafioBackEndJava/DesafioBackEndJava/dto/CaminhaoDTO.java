package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CaminhaoDTO(
		@Schema(hidden = true)
		Long id,
		@Schema(example = "AAA-2325")
		String placa,
		@Schema(example = "FORD")
		String modelo,
		@Schema(example = "2025")
		int ano) {
}
