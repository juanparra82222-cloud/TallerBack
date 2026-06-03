package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.jugador;
import com.example.demo.repositories.jugadorRepository;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*") // Simplificado temporalmente para que no te bloquee el despliegue
public class jugadorController {

    @Autowired
    private jugadorRepository jugadorRepository;

    // INYECCIÓN DEL ENCRIPTADOR DE SPRING SECURITY
    @Autowired
    private PasswordEncoder passwordEncoder; 

    // 1. Endpoint para Crear un Jugador (Registro)
    @PostMapping("/registro")
    public ResponseEntity<jugador> registrarJugador(@RequestBody jugador nuevoJugador) {
        nuevoJugador.setMonedas(0); 
        
        // ¡MAGIA DE SEGURIDAD! Encriptamos la clave antes de mandarla a Supabase
        String claveEncriptada = passwordEncoder.encode(nuevoJugador.getPassword());
        nuevoJugador.setPassword(claveEncriptada);
        
        jugador guardado = jugadorRepository.save(nuevoJugador);
        return ResponseEntity.ok(guardado);
    }

    // 2. Endpoint para el Login (Evaluado en la rúbrica)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody jugador credenciales) {
        Optional<jugador> jugadorExistente = jugadorRepository.findByUsername(credenciales.getUsername());
        
        // VALIDACIÓN SEGURA: Comparamos la clave que escribió el usuario con el Hash de la BD
        if (jugadorExistente.isPresent() && 
            passwordEncoder.matches(credenciales.getPassword(), jugadorExistente.get().getPassword())) {
            
            // Login exitoso
            return ResponseEntity.ok(jugadorExistente.get()); 
        } else {
            // Error 401 Unauthorized
            return ResponseEntity.status(401).body("Credenciales incorrectas o el usuario no existe"); 
        }
    }
}
