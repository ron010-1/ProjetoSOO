package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.Chefe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefeRepositorio extends JpaRepository<Chefe, String> {
}
