package br.com.thiagomagdalena.pacientesservico.api;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.Paciente;
import br.com.thiagomagdalena.pacientesservico.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public Flux<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    // Buscar paciente por ID
    @GetMapping("/{id}")
    public Mono<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    // Buscar pacientes por nome
    @GetMapping("/nome/{nome}")
    public Flux<Paciente> buscarPorNome(@PathVariable String nome) {
        return pacienteService.buscarPorNome(nome);
    }

    // Criar um novo paciente
    @PostMapping
    public Mono<Paciente> criarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.criarPaciente(paciente);
    }

    // Atualizar um paciente existente
    @PutMapping("/{id}")
    public Mono<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.atualizarPaciente(id, paciente);
    }

    // Deletar um paciente
    @DeleteMapping("/{id}")
    public Mono<Void> deletarPaciente(@PathVariable Long id) {
        return pacienteService.deletarPaciente(id);
    }
}
