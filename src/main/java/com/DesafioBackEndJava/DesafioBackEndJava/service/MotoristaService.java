package com.DesafioBackEndJava.DesafioBackEndJava.service;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.MotoristaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Motorista;
import com.DesafioBackEndJava.DesafioBackEndJava.exceptions.CustomException;
import com.DesafioBackEndJava.DesafioBackEndJava.exceptions.GlobalExceptions;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository motoristaRepository;

	// Criar
	@Transactional
	public MotoristaDTO criar(MotoristaDTO motoristaDTO) {
		Motorista motorista = new Motorista();
		motorista.setNome(motoristaDTO.nome());
		motorista.setCpf(motoristaDTO.cpf());

		if (motoristaRepository.findByCpf(motoristaDTO.cpf()).isPresent()) {
			throw new CustomException("Já existe um motorista cadastrado com essa CPF.", HttpStatus.NO_CONTENT.value());
		}

		Motorista salvo = motoristaRepository.save(motorista);
		return new MotoristaDTO(
				salvo.getId(),
				salvo.getNome(),
				salvo.getCpf()
		);
	}

	//listar
	@Transactional
	public List<MotoristaDTO> listarTodos() {
		return motoristaRepository.findAll()
				.stream()
				.map(this::motoristaDTO)
				.collect(Collectors.toList());
	}

	// Buscar por ID
	@Transactional
	public MotoristaDTO buscarPorId(Long id) {
		Motorista motorista = motoristaRepository.findById(id)
				.orElseThrow(() -> new CustomException("Caminhão não encontrado", HttpStatus.NOT_FOUND.value()));
		return motoristaDTO(motorista);
	}

	// Atualizar
	@Transactional
	public MotoristaDTO atualizar(Long id, MotoristaDTO motoristaDTO) {
		Motorista motorista = motoristaRepository.findById(id)
				.orElseThrow(() -> new CustomException("Caminhão não encontrado", HttpStatus.NOT_FOUND.value()));
		motorista.setCpf(motoristaDTO.cpf());
		motorista.setNome(motorista.getNome());

		return motoristaDTO(motoristaRepository.save(motorista));
	}

	// Deletar
	@Transactional
	public void deletar(Long id) {
		Motorista motorista = motoristaRepository.findById(id)
				.orElseThrow(() -> new CustomException("Caminhão não encontrado", HttpStatus.NOT_FOUND.value()));
		motoristaRepository.delete(motorista);
	}

	// Conversão para DTO
	@Transactional
	private MotoristaDTO motoristaDTO(Motorista motorista) {
		MotoristaDTO motoristaDTO = new MotoristaDTO(
				motorista.getId(),
				motorista.getNome(),
				motorista.getCpf());
		return motoristaDTO;
	}
}
