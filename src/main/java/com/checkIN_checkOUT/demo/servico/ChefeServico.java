package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.Chefe;
import com.checkIN_checkOUT.demo.repositorios.ChefeRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefeServico {
    private ChefeRepositorio chefeRepositorio;

    public ChefeServico(ChefeRepositorio chefeRepositorio) {
        this.chefeRepositorio = chefeRepositorio;
    }

    public Chefe criarChefe(Chefe chefe){
        return chefeRepositorio.save(chefe);
    }

    public List<Chefe> buscarChefe(){
        return chefeRepositorio.findAll();
    }

    public Optional<Chefe> buscarChefe(String id){
        return chefeRepositorio.findById(id);
    }

    public void atualizarChefe(Chefe chefe){
        chefeRepositorio.save(chefe);
    }

    public void excluirChefe(String id){
        chefeRepositorio.deleteById(id);
    }

    public void salvarChefe(Chefe chefe) {
    }
}
