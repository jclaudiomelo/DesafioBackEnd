package com.DesafioBackEndJava.DesafioBackEndJava.dto;

import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;

import java.time.LocalDate;

public interface TotalPorDiaDTO {
	LocalDate getData();

	String getTipoCarga();

	Double getValorTotal();
}
