package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.mejora;
import com.example.demo.repositories.mejoraRepository;

@RestController
@RequestMapping("/api/mejoras")
@CrossOrigin(origins = "http://localhost:5173")
public class mejoraController {

    @Autowired
    private mejoraRepository mejoraRepository;

    // Traer todos los objetos disponibles en la tienda
    @GetMapping("/tienda")
    public ResponseEntity<List<mejora>> obtenerCatalogoTienda() {
        List<mejora> catalogo = mejoraRepository.findAll();
        return ResponseEntity.ok(catalogo);
    }
}