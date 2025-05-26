package com.DesafioBackEndJava.DesafioBackEndJava.service;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.EntregaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Caminhao;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Entrega;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Motorista;
import com.DesafioBackEndJava.DesafioBackEndJava.enums.Regiao;
import com.DesafioBackEndJava.DesafioBackEndJava.enums.TipoCarga;
import com.DesafioBackEndJava.DesafioBackEndJava.exceptions.CustomException;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.CaminhaoRepository;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.EntregaRepository;
import com.DesafioBackEndJava.DesafioBackEndJava.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private CaminhaoRepository caminhaoRepository;

	@Autowired
	private MotoristaRepository motoristaRepository;


	//CRIAR ENTREGAS
	@Transactional
	public EntregaDTO criarEntrega(EntregaDTO caminhaoDTO) {
		Caminhao caminhao = caminhaoRepository.findById(caminhaoDTO.caminhaoId())
				.orElseThrow(() -> new CustomException("Caminhão não encontrado", HttpStatus.NOT_FOUND.value()));

		Motorista motorista = motoristaRepository.findById(caminhaoDTO.motoristaId())
				.orElseThrow(() -> new CustomException("Motorista não encontrado", HttpStatus.NOT_FOUND.value()));

		// Regra 1: Um caminhão só pode ter uma entrega ativa
		if (entregaRepository.findFirstByCaminhaoId(caminhao.getId()).isPresent()) {
			throw new CustomException("Caminhão já está em uma entrega ", HttpStatus.CONFLICT.value());
		}

		// Regras 5 e 6: Limite mensal de entregas
		YearMonth agora = YearMonth.now();
		LocalDateTime inicioMes = agora.atDay(1).atStartOfDay();
		LocalDateTime fimMes = agora.atEndOfMonth().atTime(23, 59);

		long entregasCaminhao = entregaRepository.findByCaminhaoIdAndHorarioBetween(
				caminhao.getId(),
				inicioMes,
				fimMes).size();
		long entregasMotorista = entregaRepository.findByMotoristaIdAndHorarioBetween(
				motorista.getId(),
				inicioMes,
				fimMes).size();

		if (entregasCaminhao >= 4)
			throw new CustomException("Caminhão excedeu limite mensal", HttpStatus.CONFLICT.value());
		if (entregasMotorista >= 2)
			throw new CustomException("Motorista excedeu limite mensal", HttpStatus.CONFLICT.value());

		// Regras 7, 8, 9: Aplicar taxa de região
		double valorBase = caminhaoDTO.valor();
		Regiao regiao = Regiao.fromString(caminhaoDTO.destino());
		double taxa = regiao.getTaxa();
		double valorFinal = valorBase + (valorBase * taxa);

		// Regra 10: Apenas uma entrega para o Nordeste por motorista
		if (regiao == Regiao.NORDESTE) {
			long entregasNordeste = entregaRepository.countByMotoristaIdAndDestino(motorista.getId(), "nordeste");
			if (entregasNordeste >= 1) {
				throw new CustomException("Motorista já fez uma entrega para o Nordeste", HttpStatus.CONFLICT.value());
			}
		}


		// Regra 2, 3 e 4
		Entrega entrega = new Entrega();
		entrega.setHorario(caminhaoDTO.horario());
		entrega.setDestino(caminhaoDTO.destino());
		entrega.setValor(valorFinal);
		entrega.setTipoCarga(caminhaoDTO.tipoCarga());
		entrega.setValiosa(valorFinal > 30000);
		entrega.setTemSeguro(caminhaoDTO.temSeguro());
		entrega.setPerigosa(caminhaoDTO.tipoCarga() == TipoCarga.COMBUSTIVEL);
		entrega.setCaminhao(caminhao);
		entrega.setMotorista(motorista);

		entrega = entregaRepository.save(entrega);

		return new EntregaDTO(
				entrega.getId(),
				entrega.getValor(),
				entrega.getDestino(),
				entrega.getHorario(),
				entrega.getTipoCarga(),
				entrega.isValiosa(),
				entrega.isPerigosa(),
				entrega.isTemSeguro(),
				entrega.getCaminhao().getId(),
				entrega.getMotorista().getId());
	}

	@Transactional
	public EntregaDTO atualizarEntrega(Long id, EntregaDTO entregaDTO) {
		Entrega entrega = entregaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));

		entrega.setValor(entregaDTO.valor());
		entrega.setDestino(entregaDTO.destino());
		entrega.setHorario(entregaDTO.horario());
//		entrega.setTipoCarga(entregaDTO.tipoCarga());
//		entrega.setValiosa(entregaDTO.valiosa());
//		entrega.setPerigosa(entregaDTO.perigosa());
//		entrega.setTemSeguro(entregaDTO.temSeguro());

		// Atualiza caminhão (verifica se existe)
		if (entregaDTO.caminhaoId() != null) {
			Caminhao caminhao = caminhaoRepository.findById(entregaDTO.caminhaoId())
					.orElseThrow(() -> new RuntimeException("Caminhão não encontrado com ID: " + entregaDTO.caminhaoId()));
			entrega.setCaminhao(caminhao);
		}

		// Atualiza motorista (verifica se existe)
		if (entregaDTO.motoristaId() != null) {
			Motorista motorista = motoristaRepository.findById(entregaDTO.motoristaId())
					.orElseThrow(() -> new RuntimeException("Motorista não encontrado com ID: " + entregaDTO.motoristaId()));
			entrega.setMotorista(motorista);
		}

		Entrega entregaAtualizada = entregaRepository.save(entrega);

		return new EntregaDTO(
				entregaAtualizada.getId(),
				entregaAtualizada.getValor(),
				entregaAtualizada.getDestino(),
				entregaAtualizada.getHorario(),
				entregaAtualizada.getTipoCarga(),
				entregaAtualizada.isValiosa(),
				entregaAtualizada.isPerigosa(),
				entregaAtualizada.isTemSeguro(),
				entregaAtualizada.getCaminhao() != null ? entregaAtualizada.getCaminhao().getId() : null,
				entregaAtualizada.getMotorista() != null ? entregaAtualizada.getMotorista().getId() : null
		);
	}


	//LISTAR ENTREGAS
	@Transactional(readOnly = true)
	public List<EntregaDTO> listarEntregas() {
		List<Entrega> entregas = entregaRepository.findAll();
		return entregas.stream().map(entrega -> new EntregaDTO(
				entrega.getId(),
				entrega.getValor(),
				entrega.getDestino(),
				entrega.getHorario(),
				entrega.getTipoCarga(),
				entrega.isValiosa(),
				entrega.isPerigosa(),
				entrega.isTemSeguro(),
				entrega.getCaminhao().getId(),
				entrega.getMotorista().getId()
		)).collect(Collectors.toList());
	}
}
