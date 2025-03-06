package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, String> {
    List<Funcionario> findFuncionarioByChefeId(String chefeId);
}