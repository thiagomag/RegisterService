package br.com.thiagomagdalena.pacientesservico.persistance.repository.patient;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.BaseReactiveComposedCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientRepository extends BaseReactiveComposedCrudRepository<Patient, Long> {

    Flux<Patient> findBySearchParams(PatientSearchParams patientSearchParams);

    Mono<Patient> findByIdEager(Long patientId);
}
