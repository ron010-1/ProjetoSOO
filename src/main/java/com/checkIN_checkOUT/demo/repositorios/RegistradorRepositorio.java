package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.Registrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistradorRepositorio extends JpaRepository<Registrador, String> {
}
