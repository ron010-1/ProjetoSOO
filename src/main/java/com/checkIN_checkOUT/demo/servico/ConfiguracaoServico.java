package com.checkIN_checkOUT.demo.servico;

import com.checkIN_checkOUT.demo.modelos.ConfiguracaoChefe;
import com.checkIN_checkOUT.demo.repositorios.ConfiguracaoChefeRepositorio;
import com.checkIN_checkOUT.demo.repositorios.ConfiguracaoChefeRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoServico {
    private ConfiguracaoChefeRepositorio configuracaoRepositorio;

    public ConfiguracaoServico(ConfiguracaoChefeRepositorio configuracaoRepositorio) {
        this.configuracaoRepositorio = configuracaoRepositorio;
    }

    public ConfiguracaoChefe criarConfiguracao(ConfiguracaoChefe configuracaoChefe){
        return configuracaoRepositorio.save(configuracaoChefe);
    }

    public List<ConfiguracaoChefe> buscarConfiguracao(){
        return configuracaoRepositorio.findAll();
    }

    public Optional<ConfiguracaoChefe> buscarConfiguracao(String id){
        return configuracaoRepositorio.findById(id);
    }

    public void atualizarConfiguracao(ConfiguracaoChefe configuracaoChefe){
        configuracaoRepositorio.save(configuracaoChefe);
    }

    public void excluirConfiguracao(String id){
        configuracaoRepositorio.deleteById(id);
    }
}
