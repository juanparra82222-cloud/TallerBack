package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.inventario;
import com.example.demo.repositories.inventarioRepository;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "http://localhost:5173")
public class inventarioController {

    @Autowired
    private inventarioRepository inventarioRepository;

    // 1. Endpoint para Comprar un objeto (Crear registro en el inventario)
    @PostMapping("/comprar")
    public ResponseEntity<inventario> agregarAlInventario(@RequestBody inventario nuevaCompra) {
        // Guarda la relación de qué jugador compró qué mejora
        inventario guardado = inventarioRepository.save(nuevaCompra);
        return ResponseEntity.ok(guardado);
    }

    // 2. Endpoint para ver el inventario de un jugador específico
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<inventario>> obtenerInventarioDeJugador(@PathVariable Integer jugadorId) {
        // Usamos el método personalizado que creamos en el Repository
        List<inventario> miInventario = inventarioRepository.findByJugadorId(jugadorId);
        return ResponseEntity.ok(miInventario);
    }
}