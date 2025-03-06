package com.checkIN_checkOUT.demo.controlador;

import com.checkIN_checkOUT.demo.servico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.checkIN_checkOUT.demo.modelos.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MainController {

    private final ChefeServico chefeServico;
    private FuncionarioServico funcionarioServico;
    private CheckinCheckoutServico checkinCheckoutServico;
    private ConfiguracaoServico configuracaoServico;
    private RegistradorServico registradorServico;

    @Autowired
    public MainController(ChefeServico chefeServico, FuncionarioServico funcionarioServico, CheckinCheckoutServico checkinCheckoutServico, ConfiguracaoServico configuracaoServico, RegistradorServico registradorServico) {
        this.chefeServico = chefeServico;
        this.funcionarioServico = funcionarioServico;
        this.checkinCheckoutServico = checkinCheckoutServico;
        this.configuracaoServico = configuracaoServico;
        this.registradorServico = registradorServico;
    }

    @PostMapping("/chefe")
    public Chefe criarChefe(@RequestBody Chefe chefe) {
        return chefeServico.criarChefe(chefe);
    }

    @GetMapping("/chefe")
    public List<Chefe> buscarChefe() {
        List<Chefe> chefes = chefeServico.buscarChefe();
        for(Chefe chefe : chefes) {
            chefe.setFuncionarios(funcionarioServico.buscarFuncionariosPorChefe(chefe.getId()));
        }
        return chefes;
    }

    @PostMapping("/contratar/{chefeId}")
    public Funcionario contratarFuncionario(@PathVariable String chefeId, @RequestBody Funcionario funcionario) {
        Chefe chefe = chefeServico.buscarChefe(chefeId).orElseThrow(() -> new RuntimeException(("Chefe não encontrado")));
        funcionario.setChefe(chefe);
        return funcionarioServico.criarFuncionario(funcionario);
    }

    @PostMapping("/checkin/{funcionarioId}")
    public RegistroCheckinCheckout realizarCheckin(@PathVariable Long funcionarioId) {
        Funcionario funcionario = funcionarioServico.buscarFuncionario(String.valueOf(funcionarioId))
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return checkinCheckoutServico.realizarCheckin(funcionario);
    }

    @PostMapping("/checkout/{funcionarioId}")
    public RegistroCheckinCheckout realizarCheckout(@PathVariable Long funcionarioId) {
        Funcionario funcionario = funcionarioServico.buscarFuncionario(String.valueOf(funcionarioId))
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return checkinCheckoutServico.realizarCheckout(funcionario);
    }

    @GetMapping("/registros")
    public List<RegistroCheckinCheckout> listarRegistros() {
        return checkinCheckoutServico.buscarCheckinCheckout();
    }

    @GetMapping("/registro/{id}")
    public RegistroCheckinCheckout buscarRegistro(@PathVariable Long id) {
        return checkinCheckoutServico.buscarCheckinCheckout(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
    }

    @DeleteMapping("/registro/{id}")
    public void excluirRegistro(@PathVariable Long id) {
        checkinCheckoutServico.excluirCheckinCheckout(id);
    }

    @PostMapping("/configuracao/{chefeId}")
    public <Configuracao> Configuracao criarConfiguracao(@PathVariable Long chefeId, @RequestBody Configuracao configuracao) {
        Chefe chefe = chefeServico.buscarChefe(String.valueOf(chefeId))
                .orElseThrow(() -> new RuntimeException("Chefe não encontrado"));
        configuracao.getClass();
        return (Configuracao) configuracaoServico.criarConfiguracao((ConfiguracaoChefe) configuracao);
    }

    // Criar um Registrador associado a um Chefe
    @PostMapping("/registrador/{chefeId}")
    public ResponseEntity<?> criarRegistrador(@PathVariable String chefeId, @RequestBody Registrador registrador) {
        Optional<Chefe> chefeOptional = chefeServico.buscarChefe(chefeId);
        if (chefeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Chefe não encontrado.");
        }

        // Associar o registrador ao chefe e salvar no banco
        registrador.setChefe(chefeOptional.get());
        Registrador novoRegistrador = registradorServico.criarRegistrador(registrador);

        return ResponseEntity.ok(novoRegistrador);
    }

    //Chefe define a configuração
    @PutMapping("/configuracao/{chefeId}/banco-horas")
    public ResponseEntity<?> configurarBancoHoras(@PathVariable String chefeId, @RequestParam boolean permitir) {
        Optional<Chefe> chefeOptional = chefeServico.buscarChefe(chefeId);
        if (chefeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Chefe não encontrado.");
        }

        Chefe chefe = chefeOptional.get();
        ConfiguracaoChefe configuracao = chefe.getConfiguracao();

        // Se não existir uma configuração, cria uma nova
        if (configuracao == null) {
            configuracao = new ConfiguracaoChefe();
            chefe.setConfiguracao(configuracao); // Associa ao chefe
        }

        // Atualiza a configuração do banco de horas
        configuracao.setPermitirBancoHoras(permitir);

        // Salva a configuração e atualiza o chefe
        configuracaoServico.criarConfiguracao(configuracao);
        chefeServico.salvarChefe(chefe); // Salvar para garantir a atualização no banco

        return ResponseEntity.ok("Configuração de banco de horas atualizada com sucesso.");
    }
}
