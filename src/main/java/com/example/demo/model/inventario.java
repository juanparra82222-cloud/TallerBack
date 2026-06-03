package com.example.demo.model;
import jakarta.persistence.*;


@Entity
@Table(name = "inventario")
public class inventario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private jugador jugador;

    @ManyToOne
    @JoinColumn(name = "mejora_id", nullable = false)
    private mejora mejora;


    public inventario()
    {

    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public jugador getJugador() {
        return jugador;
    }


    public void setJugador(jugador jugador) {
        this.jugador = jugador;
    }


    public mejora getMejora() {
        return mejora;
    }


    public void setMejora(mejora mejora) {
        this.mejora = mejora;
    }

    
}
