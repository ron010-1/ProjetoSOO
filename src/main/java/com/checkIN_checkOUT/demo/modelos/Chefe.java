package com.checkIN_checkOUT.demo.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Chefe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String nome;
    private String email;
    private int senha;
    @OneToMany
    private List<Funcionario> funcionarios;

    @OneToOne
    @JoinColumn(name = "configuracao_id")
    private ConfiguracaoChefe configuracao;

    @OneToMany(mappedBy = "chefe")
    private List<Registrador> registradores;

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Chefe() {

    }

    public Chefe(String id, String nome, String email, int senha) {
        Id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public ConfiguracaoChefe getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(ConfiguracaoChefe configuracao) {
        this.configuracao = configuracao;
    }

}