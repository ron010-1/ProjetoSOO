package com.checkIN_checkOUT.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String nome;
    private String email;
    private int senha;
    @ManyToOne
    @JoinColumn(name = "chefe_id")
    @JsonIgnore
    private Chefe chefe;

    public Chefe getChefe() {return chefe;}

    public void setChefe(Chefe chefe) {this.chefe = chefe;}

    public Funcionario() { }

    public Funcionario(String id, String nome, String email, int senha) {
        Id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {return Id;}

    public void setId(String id) {Id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getSenha() {return senha;}

    public void setSenha(int senha) {this.senha = senha;}
}
