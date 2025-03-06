package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.RegistroCheckinCheckout;
import com.checkIN_checkOUT.demo.modelos.Funcionario;
import com.checkIN_checkOUT.demo.repositorios.CheckinCheckoutRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CheckinCheckoutServico {
    private final CheckinCheckoutRepositorio checkinCheckoutRepositorio;

    public CheckinCheckoutServico(CheckinCheckoutRepositorio checkinCheckoutRepositorio) {
        this.checkinCheckoutRepositorio = checkinCheckoutRepositorio;
    }

    /**
     * Registra um check-in para um funcionário
     */
    public RegistroCheckinCheckout realizarCheckin(Funcionario funcionario) {
        // Verifica se já existe um check-in sem check-out para o funcionário
        Optional<RegistroCheckinCheckout> ultimoRegistro = checkinCheckoutRepositorio
                .findTopByFuncionarioAndDataHoraSaidaIsNullOrderByDataHoraEntradaDesc(funcionario);

        if (ultimoRegistro.isPresent()) {
            throw new RuntimeException("Funcionário já possui um check-in aberto.");
        }

        RegistroCheckinCheckout checkin = new RegistroCheckinCheckout();
        checkin.setFuncionario(funcionario);
        checkin.setDataHoraEntrada(LocalDateTime.now());

        return checkinCheckoutRepositorio.save(checkin);
    }

    /**
     * Registra um check-out para um funcionário
     */
    public RegistroCheckinCheckout realizarCheckout(Funcionario funcionario) {
        // Busca o último check-in sem saída
        RegistroCheckinCheckout registro = checkinCheckoutRepositorio
                .findTopByFuncionarioAndDataHoraSaidaIsNullOrderByDataHoraEntradaDesc(funcionario)
                .orElseThrow(() -> new RuntimeException("Nenhum check-in aberto encontrado para este funcionário."));

        registro.setDataHoraSaida(LocalDateTime.now());
        return checkinCheckoutRepositorio.save(registro);
    }

    /**
     * Busca todos os registros de check-in/check-out
     */
    public List<RegistroCheckinCheckout> buscarCheckinCheckout() {
        return checkinCheckoutRepositorio.findAll();
    }

    /**
     * Busca um registro específico pelo ID
     */
    public Optional<RegistroCheckinCheckout> buscarCheckinCheckout(Long id) {
        return checkinCheckoutRepositorio.findById(String.valueOf(id));
    }

    /**
     * Remove um registro pelo ID
     */
    public void excluirCheckinCheckout(Long id) {
        checkinCheckoutRepositorio.deleteById(String.valueOf(id));
    }

    public RegistroCheckinCheckout criarCheckinCheckout(RegistroCheckinCheckout checkin) {
        return checkin;
    }

    public void atualizarCheckinCheckout(RegistroCheckinCheckout registro) {
    }
}
