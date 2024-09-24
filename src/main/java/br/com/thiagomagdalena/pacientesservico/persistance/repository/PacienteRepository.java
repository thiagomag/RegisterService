package br.com.thiagomagdalena.pacientesservico.persistance.repository;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.Paciente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PacienteRepository extends ReactiveCrudRepository<Paciente, Long> {

    // Encontrar pacientes por nome
    Flux<Paciente> findByNome(String nome);

    // Encontrar pacientes por CPF
    Flux<Paciente> findByCpf(String cpf);
}
