package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.BancoDeHoras;
import com.checkIN_checkOUT.demo.modelos.Funcionario;
import com.checkIN_checkOUT.demo.modelos.Chefe;
import com.checkIN_checkOUT.demo.repositorios.BancoHorasRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasServico {
    private BancoHorasRepositorio bancoHorasRepositorio;

    public BancoHorasServico(BancoHorasRepositorio bancoHorasRepositorio) {
        this.bancoHorasRepositorio = bancoHorasRepositorio;
    }

    /**
     * Verifica se o banco de horas é permitido antes de criar um novo registro.
     */
    public BancoDeHoras criarBancoDeHoras(BancoDeHoras bancoDeHoras) {
        Funcionario funcionario = bancoDeHoras.getFuncionario();
        if (funcionario == null || funcionario.getChefe() == null) {
            throw new RuntimeException("Funcionário ou Chefe não encontrado.");
        }

        Chefe chefe = funcionario.getChefe();
        if (chefe.getConfiguracao() == null || !chefe.getConfiguracao().isPermitirBancoHoras()) {
            throw new RuntimeException("Banco de horas não permitido para este chefe.");
        }

        return bancoHorasRepositorio.save(bancoDeHoras);
    }

    public List<BancoDeHoras> buscarBancoDeHoras() {
        return bancoHorasRepositorio.findAll();
    }

    public Optional<BancoDeHoras> buscarBancoDeHoras(String id){
        return bancoHorasRepositorio.findById(id);
    }

    /**
     * Atualiza um registro de banco de horas, verificando a configuração do chefe.
     */
    public void atualizarBancoDeHoras(BancoDeHoras bancoDeHoras) {
        Funcionario funcionario = bancoDeHoras.getFuncionario();
        if (funcionario == null || funcionario.getChefe() == null) {
            throw new RuntimeException("Funcionário ou Chefe não encontrado.");
        }

        Chefe chefe = funcionario.getChefe();
        if (chefe.getConfiguracao() == null || !chefe.getConfiguracao().isPermitirBancoHoras()) {
            throw new RuntimeException("Banco de horas não permitido para este chefe.");
        }

        bancoHorasRepositorio.save(bancoDeHoras);
    }

    public void excluirBancoDeHoras(String id){
        bancoHorasRepositorio.deleteById(id);
    }
}
