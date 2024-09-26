package br.com.thiagomagdalena.registerservice.persistance.repository.patient;

import br.com.thiagomagdalena.registerservice.persistance.domain.patient.PatientAddress;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PatientAddressCrudRepository extends BaseReactiveCrudRepository<PatientAddress, Long> {

    Flux<PatientAddress> findPatientAddressByPatientId(Long patientId);

}
