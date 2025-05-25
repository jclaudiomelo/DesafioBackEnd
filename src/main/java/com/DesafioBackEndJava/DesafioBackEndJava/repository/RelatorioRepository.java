package com.DesafioBackEndJava.DesafioBackEndJava.repository;

import com.DesafioBackEndJava.DesafioBackEndJava.dto.RelatorioEntregaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.dto.TotalPorDiaDTO;
import com.DesafioBackEndJava.DesafioBackEndJava.entities.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Entrega, Long> {

	@Query("""
			    SELECT new com.DesafioBackEndJava.DesafioBackEndJava.dto.RelatorioEntregaDTO(
			        e.caminhao.id,
			        e.caminhao.placa,
			        e.horario,
			        e.tipoCarga,
			        e.valor
			    )
			    FROM Entrega e
			    WHERE e.horario BETWEEN :inicio AND :fim
			""")
	List<RelatorioEntregaDTO> findRelatorioEntregas(
			@Param("inicio") LocalDateTime inicio,
			@Param("fim") LocalDateTime fim
	);

	@Query(value = """
			SELECT 
			    DATE(horario) as data,
			    tipo_carga as tipoCarga,
			    SUM(valor) as valorTotal
			FROM entrega
			WHERE horario BETWEEN :inicio AND :fim
			GROUP BY DATE(horario), tipo_carga
			ORDER BY DATE(horario), tipo_carga
			""", nativeQuery = true)
	List<TotalPorDiaDTO> findTotaisPorDia(
			@Param("inicio") LocalDateTime inicio,
			@Param("fim") LocalDateTime fim
	);


}
