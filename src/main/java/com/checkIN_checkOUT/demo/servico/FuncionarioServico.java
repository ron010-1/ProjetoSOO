package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.Funcionario;
import com.checkIN_checkOUT.demo.repositorios.FuncionarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServico {
    private FuncionarioRepositorio funcionarioRepositorio;

    public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
    }

    public Funcionario criarFuncionario(Funcionario funcionario){
        return funcionarioRepositorio.save(funcionario);
    }

    public List<Funcionario> buscarFuncionario(){
        return funcionarioRepositorio.findAll();
    }

    public Optional<Funcionario> buscarFuncionario(String id){
        return funcionarioRepositorio.findById(id);
    }

    public List<Funcionario> buscarFuncionariosPorChefe(String chefeId) {
        return funcionarioRepositorio.findFuncionarioByChefeId(chefeId);
    }
    public void atualizarFuncionario(Funcionario funcionario){
        funcionarioRepositorio.save(funcionario);
    }

    public void excluirFuncionario(String id){
        funcionarioRepositorio.deleteById(id);
    }
}
