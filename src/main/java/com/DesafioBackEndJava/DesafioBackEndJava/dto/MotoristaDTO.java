package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MotoristaDTO(
		@Schema(hidden = true)
		Long id,
		@Schema(example = "Joao da silva")
		String nome,
		@Schema(example = "12345678910")
		String cpf) {
}
