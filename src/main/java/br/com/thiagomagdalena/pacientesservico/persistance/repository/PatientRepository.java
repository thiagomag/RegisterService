package br.com.thiagomagdalena.pacientesservico.persistance.repository;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientRepository extends ReactiveCrudRepository<Patient, Long> {

    Flux<Patient> findByName(String name);

    Flux<Patient> findByCpf(String cpf);

    Mono<Void> softDelete(Patient patient);
}
