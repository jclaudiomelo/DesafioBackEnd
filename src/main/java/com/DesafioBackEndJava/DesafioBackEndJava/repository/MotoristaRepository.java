package com.DesafioBackEndJava.DesafioBackEndJava.repository;

import com.DesafioBackEndJava.DesafioBackEndJava.entities.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
	Optional<Motorista> findByCpf(String cpf);
}