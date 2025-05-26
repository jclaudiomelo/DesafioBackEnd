package com.DesafioBackEndJava.DesafioBackEndJava.controller;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.EntregaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.exceptions.CustomException;
import com.DesafioBackEndJava.DesafioBackEndJava.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	@Autowired
	private EntregaService entregaService;

	@PostMapping("/criar")
	public ResponseEntity<EntregaDTO> criar(@RequestBody EntregaDTO entregaDTO) {
		return ResponseEntity.ok(entregaService.criarEntrega(entregaDTO));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<EntregaDTO>> listar() {
		return ResponseEntity.ok(entregaService.listarEntregas());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<List<EntregaDTO>> buscarPorId() {
		return ResponseEntity.ok(entregaService.listarEntregas());
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<EntregaDTO> atualizar(
			@RequestBody EntregaDTO entregaDTO, @PathVariable Long id) {
		return ResponseEntity.ok(entregaService.atualizarEntrega(id, entregaDTO));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		entregaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
