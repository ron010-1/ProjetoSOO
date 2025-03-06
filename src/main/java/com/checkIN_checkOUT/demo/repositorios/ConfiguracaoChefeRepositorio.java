package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.ConfiguracaoChefe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracaoChefeRepositorio extends JpaRepository<ConfiguracaoChefe, String> {
}
