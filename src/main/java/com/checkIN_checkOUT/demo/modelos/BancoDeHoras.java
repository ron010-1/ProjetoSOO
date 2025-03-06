package com.checkIN_checkOUT.demo.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "banco_de_horas")
public class BancoDeHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorHora;
    private BigDecimal horasAcumuladas;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public BancoDeHoras() {
    }

    public BancoDeHoras(Long id, BigDecimal valorHora, BigDecimal horasAcumuladas) {
        this.id = id;
        this.valorHora = valorHora;
        this.horasAcumuladas = horasAcumuladas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(BigDecimal horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

