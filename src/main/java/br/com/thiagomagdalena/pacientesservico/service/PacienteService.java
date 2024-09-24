package br.com.thiagomagdalena.pacientesservico.service;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.Paciente;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public Flux<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Mono<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Flux<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findByNome(nome);
    }

    public Mono<Paciente> criarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Mono<Paciente> atualizarPaciente(Long id, Paciente novoPaciente) {
        return pacienteRepository.findById(id)
                .flatMap(pacienteExistente -> {
                    pacienteExistente.setNome(novoPaciente.getNome());
                    pacienteExistente.setCpf(novoPaciente.getCpf());
                    pacienteExistente.setDataNascimento(novoPaciente.getDataNascimento());
                    pacienteExistente.setEmail(novoPaciente.getEmail());
                    pacienteExistente.setTelefone(novoPaciente.getTelefone());
                    pacienteExistente.setEndereco(novoPaciente.getEndereco());
                    return pacienteRepository.save(pacienteExistente);
                });
    }

    public Mono<Void> deletarPaciente(Long id) {
        return pacienteRepository.deleteById(id);
    }
}
