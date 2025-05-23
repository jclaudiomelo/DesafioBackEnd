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
		caminhao.setPlaca(caminhaoDTO.placa());
		caminhao.setModelo(caminhaoDTO.modelo());
		caminhao.setAno(caminhaoDTO.ano());

		if (caminhaoRepository.findByPlaca(caminhaoDTO.placa()).isPresent()) {
			throw new RuntimeException("Já existe um caminhão cadastrado com essa placa.");
		}

		Caminhao salvo = caminhaoRepository.save(caminhao);
		return caminhaoDTO(salvo);
	}

	// Listar todos
	@Transactional
	public List<CaminhaoDTO> listarTodos() {
		return caminhaoRepository.findAll()
				.stream()
				.map(this::caminhaoDTO)
				.collect(Collectors.toList());
	}

	// Buscar por ID
	@Transactional
	public CaminhaoDTO buscarPorId(Long id) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
		return caminhaoDTO(caminhao);
	}

	// Atualizar
	@Transactional
	public CaminhaoDTO atualizar(Long id, CaminhaoDTO caminhaoDTO) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
		caminhao.setPlaca(caminhaoDTO.placa());
		caminhao.setModelo(caminhaoDTO.modelo());
		caminhao.setAno(caminhaoDTO.ano());

		return caminhaoDTO(caminhaoRepository.save(caminhao));
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
	private CaminhaoDTO caminhaoDTO(Caminhao caminhao) {
		CaminhaoDTO caminhaoDTO = new CaminhaoDTO(
				caminhao.getId(),
				caminhao.getPlaca(),
				caminhao.getModelo(),
				caminhao.getAno());

		return caminhaoDTO;
	}
}
