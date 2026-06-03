package com.example.demo.repositories;

import com.example.demo.model.jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface jugadorRepository extends JpaRepository<jugador, Integer> {
    // Spring Boot crea automáticamente la consulta SQL con solo nombrar el método así:
    Optional<jugador> findByUsername(String username);
}