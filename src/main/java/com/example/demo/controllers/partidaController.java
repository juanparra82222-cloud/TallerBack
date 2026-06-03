package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.partida;
import com.example.demo.repositories.partidaRepository;

@RestController
@RequestMapping("/api/partidas")
@CrossOrigin(origins = "http://localhost:5173") // Recordamos dar permiso a React
public class partidaController {

    @Autowired
    private partidaRepository partidaRepository;

    // Guardar el resultado cuando el jugador muere en el juego
    @PostMapping("/guardar")
    public ResponseEntity<partida> guardarPartida(@RequestBody partida nuevaPartida) {
        partida guardada = partidaRepository.save(nuevaPartida);
        return ResponseEntity.ok(guardada);
    }

    // Traer los 10 mejores puntajes para la pantalla de Home
    @GetMapping("/leaderboard")
    public ResponseEntity<List<partida>> obtenerLeaderboard() {
        // ¿Recuerdas el método que creamos en el Repository? Aquí lo usamos.
        List<partida> top10 = partidaRepository.findTop10ByOrderByPuntajeDesc();
        return ResponseEntity.ok(top10);
    }
}