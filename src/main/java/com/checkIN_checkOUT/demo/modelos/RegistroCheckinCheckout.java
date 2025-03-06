package com.checkIN_checkOUT.demo.modelos;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_checkin_checkout")
public class RegistroCheckinCheckout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;


    public Registrador getRegistrador() {
        return registrador;
    }

    public void setRegistrador(Registrador registrador) {
        this.registrador = registrador;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "registrador_id")
    private Registrador registrador;

    public void setFuncionario(Funcionario funcionario) {
    }

    public void setDataHoraEntrada(LocalDateTime now) {
    }

    public void setDataHoraSaida(LocalDateTime now) {
    }

    public boolean dataHoraSaida() {
        return false;
    }
}

