package com.DesafioBackEndJava.DesafioBackEndJava.repository;

import com.DesafioBackEndJava.DesafioBackEndJava.entities.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

	List<Entrega> findByCaminhaoIdAndHorarioBetween(Long caminhaoId, LocalDateTime inicio, LocalDateTime fim);

	List<Entrega> findByMotoristaIdAndHorarioBetween(Long motoristaId, LocalDateTime inicio, LocalDateTime fim);

	Optional<Entrega> findFirstByCaminhaoId(Long caminhaoId);


	List<Entrega> findByCaminhaoId(Long caminhaoId);

	List<Entrega> findByMotoristaId(Long motoristaId);

	long countByMotoristaIdAndDestino(Long motoristaId, String destino);

//	boolean findByCaminhaoId(Long caminhaoId);
}