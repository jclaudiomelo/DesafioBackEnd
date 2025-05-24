package com.DesafioBackEndJava.DesafioBackEndJava.controller;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.RelatorioEntregaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.dto.TotalPorDiaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

	@Autowired
	private RelatorioService relatorioService;

	// ✅ Endpoint 1: Total de caminhões na frota
	@GetMapping("/frota/total")
	public ResponseEntity<Long> getTotalCaminhoes() {
		return ResponseEntity.ok(relatorioService.contarCaminhoes());
	}

	// ✅ Endpoint 2: Entregas realizadas com horário e tipo de carga
	@GetMapping("/entregas")
	public ResponseEntity<List<RelatorioEntregaDTO>> getEntregasPeriodo(
			@RequestParam LocalDate inicio,
			@RequestParam LocalDate fim) {

		return ResponseEntity.ok(relatorioService.listarEntregasPeriodo(inicio, fim));
	}

	// ✅ Endpoint 3: Valores totais por dia
	@GetMapping("/valores-por-dia")
	public ResponseEntity<List<TotalPorDiaDTO>> getTotaisPorDia(
			@RequestParam LocalDate inicio,
			@RequestParam LocalDate fim) {

		return ResponseEntity.ok(relatorioService.calcularTotaisPorDia(inicio, fim));
	}
}
