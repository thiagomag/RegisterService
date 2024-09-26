package br.com.thiagomagdalena.registerservice.persistance.repository.patient;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveComposedCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientRepository extends BaseReactiveComposedCrudRepository<Patient, Long> {

    Flux<Patient> findBySearchParams(PatientSearchParams patientSearchParams);

    Mono<Patient> findByIdEager(Long patientId);
}
