package com.DesafioBackEndJava.DesafioBackEndJava.service;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.CaminhaoDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Caminhao;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaminhaoService {

	@Autowired
	private CaminhaoRepository caminhaoRepository;

	// Criar
	@Transactional
	public CaminhaoDTO criar(CaminhaoDTO caminhaoDTO) {
		Caminhao caminhao = new Caminhao();
		caminhao.setPlaca(caminhaoDTO.getPlaca());
		caminhao.setModelo(caminhaoDTO.getModelo());
		caminhao.setAno(caminhaoDTO.getAno());

		Caminhao salvo = caminhaoRepository.save(caminhao);
		return toDTO(salvo);
	}

	// Listar todos
	@Transactional
	public List<CaminhaoDTO> listarTodos() {
		return caminhaoRepository.findAll()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

	// Buscar por ID
	@Transactional
	public CaminhaoDTO buscarPorId(Long id) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
		return toDTO(caminhao);
	}

	// Atualizar
	@Transactional
	public CaminhaoDTO atualizar(Long id, CaminhaoDTO dto) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));

		caminhao.setPlaca(dto.getPlaca());
		caminhao.setModelo(dto.getModelo());
		caminhao.setAno(dto.getAno());

		return toDTO(caminhaoRepository.save(caminhao));
	}

	// Deletar
	@Transactional
	public void deletar(Long id) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
		caminhaoRepository.delete(caminhao);
	}

	// Conversão para DTO
	@Transactional
	private CaminhaoDTO toDTO(Caminhao caminhao) {
		CaminhaoDTO dto = new CaminhaoDTO();
		dto.setId(caminhao.getId());
		dto.setPlaca(caminhao.getPlaca());
		dto.setModelo(caminhao.getModelo());
		dto.setAno(caminhao.getAno());
		return dto;
	}
}
