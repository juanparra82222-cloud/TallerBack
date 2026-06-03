package com.example.demo.repositories;


import com.example.demo.model.mejora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mejoraRepository extends JpaRepository<mejora, Integer> {
}