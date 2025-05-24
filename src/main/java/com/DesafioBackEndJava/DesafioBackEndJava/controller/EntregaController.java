package com.DesafioBackEndJava.DesafioBackEndJava.controller;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.EntregaDTO;
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
	public ResponseEntity<EntregaDTO>criar(@RequestBody EntregaDTO entregaDTO){
		return ResponseEntity.ok(entregaService.criarEntrega(entregaDTO));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<EntregaDTO>>listar(){
		return ResponseEntity.ok(entregaService.listarEntregas());
	}
}
