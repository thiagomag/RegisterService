package br.com.thiagomagdalena.pacientesservico.persistance.repository;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.PatientAddress;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientAddressRepository extends ReactiveCrudRepository<PatientAddress, Long> {
}
