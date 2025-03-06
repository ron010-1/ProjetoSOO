package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.Registrador;
import com.checkIN_checkOUT.demo.repositorios.RegistradorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistradorServico {
    private RegistradorRepositorio registradorRepositorio;

    public RegistradorServico(RegistradorRepositorio registradorRepositorio) {
        this.registradorRepositorio = registradorRepositorio;
    }

    public Registrador criarRegistrador(Registrador registrador){
        return registradorRepositorio.save(registrador);
    }

    public List<Registrador> buscarRegistrador(){
        return registradorRepositorio.findAll();
    }

    public Optional<Registrador> buscarRegistrador(String id){
        return registradorRepositorio.findById(id);
    }

    public void atualizarChefe(Registrador registrador){
        registradorRepositorio.save(registrador);
    }

    public void excluirRegistrador(String id){
        registradorRepositorio.deleteById(id);
    }
}
