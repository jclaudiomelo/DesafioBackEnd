package com.DesafioBackEndJava.DesafioBackEndJava.service;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.RelatorioEntregaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.dto.RelatorioEntregaPorFiltroDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.dto.TotalPorDiaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.CaminhaoRepository;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.EntregaRepository;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private CaminhaoRepository caminhaoRepository;

	@Autowired
	private RelatorioRepository relatorioRepository;

	//total caminhoes
	@Transactional
	public long contarCaminhoes() {
		return caminhaoRepository.count();
	}


	//listar entregas
	@Transactional
	public List<RelatorioEntregaDTO> listarEntregasPeriodo(LocalDate inicio, LocalDate fim) {
		return relatorioRepository.findRelatorioEntregas(
				inicio.atStartOfDay(), fim.atTime(23, 59, 59));
	}

	//calcular totais
	@Transactional
	public List<TotalPorDiaDTO> calcularTotaisPorDia(LocalDate inicio, LocalDate fim) {
		return relatorioRepository.findTotaisPorDia(
				inicio.atStartOfDay(), fim.atTime(23, 59, 59));
	}

	@Transactional
	public List<RelatorioEntregaPorFiltroDTO> relatorioPorCaminhao(Long caminhaoId) {
		return entregaRepository.findByCaminhaoId(caminhaoId).stream()
				.map(e -> new RelatorioEntregaPorFiltroDTO(
						e.getId(),
						e.getCaminhao().getPlaca(),
						e.getDestino(),
						e.getHorario(),
						e.getTipoCarga(),
						e.getValor()
				)).toList();
	}

	@Transactional
	public List<RelatorioEntregaPorFiltroDTO> relatorioPorMotorista(Long motoristaId) {
		return entregaRepository.findByMotoristaId(motoristaId).stream()
				.map(e -> new RelatorioEntregaPorFiltroDTO(
						e.getId(),
						e.getCaminhao().getPlaca(),
						e.getDestino(),
						e.getHorario(),
						e.getTipoCarga(),
						e.getValor()
				)).toList();
	}
}
