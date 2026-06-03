package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.partida;

@Repository
public interface partidaRepository extends JpaRepository<partida, Integer> {
    // Para traer el Leaderboard (Top 10 puntajes de mayor a menor)
    List<partida> findTop10ByOrderByPuntajeDesc();
}