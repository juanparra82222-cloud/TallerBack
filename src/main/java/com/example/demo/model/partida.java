package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "partida")
public class partida 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer puntaje;
    
    @Column(name = "oleada_maxima")
    private Integer oleadaMaxima;

    // Relación: Muchas partidas pertenecen a un jugador
    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private jugador jugador;


    public partida()
    {

    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPuntaje() {
        return puntaje;
    }


    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }


    public Integer getOleadaMaxima() {
        return oleadaMaxima;
    }


    public void setOleadaMaxima(Integer oleadaMaxima) {
        this.oleadaMaxima = oleadaMaxima;
    }


    public jugador getJugador() {
        return jugador;
    }


    public void setJugador(jugador jugador) {
        this.jugador = jugador;
    }

    
}
