package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.inventario;

@Repository
public interface inventarioRepository extends JpaRepository<inventario, Integer> {
    // Para saber qué mejoras ha comprado un jugador específico
    List<inventario> findByJugadorId(Integer jugadorId);
}