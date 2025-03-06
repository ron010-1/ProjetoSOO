package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.BancoDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BancoHorasRepositorio extends JpaRepository<BancoDeHoras, String> {
}
