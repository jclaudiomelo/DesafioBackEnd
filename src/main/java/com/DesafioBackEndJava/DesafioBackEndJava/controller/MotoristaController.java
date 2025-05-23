package com.DesafioBackEndJava.DesafioBackEndJava.controller;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.MotoristaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
	@Autowired
	private MotoristaService motoristaService;

	@PostMapping("/criar")
	public ResponseEntity<MotoristaDTO> criar(@RequestBody MotoristaDTO motoristaDTO) {
		MotoristaDTO motoristaNovo = motoristaService.criar(motoristaDTO);
		return ResponseEntity.ok(motoristaNovo);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<MotoristaDTO>> listarTodos() {
		return ResponseEntity.ok(motoristaService.listarTodos());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<MotoristaDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(motoristaService.buscarPorId(id));
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<MotoristaDTO> atualizar(@PathVariable Long id, @RequestBody MotoristaDTO motoristaDTO) {
		return ResponseEntity.ok(motoristaService.atualizar(id, motoristaDTO));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		motoristaService.deletar(id);
		return ResponseEntity.noContent().build();
	}


}
