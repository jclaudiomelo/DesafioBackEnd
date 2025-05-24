package com.DesafioBackEndJava.DesafioBackEndJava.controller;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.CaminhaoDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {

	@Autowired
	private CaminhaoService caminhaoService;

	@PostMapping("/criar")
	public ResponseEntity<CaminhaoDTO> criar(@RequestBody CaminhaoDTO caminhaoDTO) {
		CaminhaoDTO novo = caminhaoService.criar(caminhaoDTO);
		return ResponseEntity.ok(novo);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<CaminhaoDTO>> listarTodos() {
		return ResponseEntity.ok(caminhaoService.listarTodos());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<CaminhaoDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(caminhaoService.buscarPorId(id));
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<CaminhaoDTO> atualizar(@PathVariable Long id, @RequestBody CaminhaoDTO caminhaoDTO) {
		return ResponseEntity.ok(caminhaoService.atualizar(id, caminhaoDTO));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		caminhaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}


}
