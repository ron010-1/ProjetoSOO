package com.checkIN_checkOUT.demo.repositorios;

import com.checkIN_checkOUT.demo.modelos.Funcionario;
import com.checkIN_checkOUT.demo.modelos.RegistroCheckinCheckout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinCheckoutRepositorio extends JpaRepository<RegistroCheckinCheckout, String> {
    Optional<RegistroCheckinCheckout> findTopByFuncionarioAndDataHoraSaidaIsNullOrderByDataHoraEntradaDesc(Funcionario funcionario);
}
