package com.checkIN_checkOUT.demo.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "registrador")
public class Registrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "chefe_id")
    private Chefe chefe;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getLocalizacao() {return localizacao;}

    public void setLocalizacao(String localizacao) {this.localizacao = localizacao;}

    public Chefe getChefe() {return chefe;}

    public void setChefe(Chefe chefe) {this.chefe = chefe;}

    public List<RegistroCheckinCheckout> getRegistros() {return registros;}

    public void setRegistros(List<RegistroCheckinCheckout> registros) {this.registros = registros;}

    @OneToMany(mappedBy = "registrador")
    private List<RegistroCheckinCheckout> registros;
}
