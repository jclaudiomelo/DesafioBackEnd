package com.DesafioBackEndJava.DesafioBackEndJava.repository;

import com.DesafioBackEndJava.DesafioBackEndJava.entities.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
//	Optional<Caminhao> findPlaca(String placa);
}
