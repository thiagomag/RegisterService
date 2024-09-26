package br.com.thiagomagdalena.registerservice.persistance.repository.patient.impl;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.patient.PatientAddressCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.patient.PatientCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.patient.PatientRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final PatientCrudRepository patientCrudRepository;
    private final PatientAddressCrudRepository patientAddressCrudRepository;


    public PatientRepositoryImpl(PatientCrudRepository patientCrudRepository,
                                 PatientAddressCrudRepository patientAddressCrudRepository) {
        this.patientCrudRepository = patientCrudRepository;
        this.patientAddressCrudRepository = patientAddressCrudRepository;
    }

    @Override
    public BaseReactiveCrudRepository<Patient, Long> getRepository() {
        return patientCrudRepository;
    }

    @Override
    public Flux<Patient> findBySearchParams(PatientSearchParams patientSearchParams) {
        final var name = Optional.ofNullable(patientSearchParams.getName())
                .map(String::toLowerCase)
                .map(s -> "%" + s + "%")
                .orElse("");
        final var cpf = Optional.ofNullable(patientSearchParams.getCpf()).orElse("");
        return patientCrudRepository.findByRequestSearchParams(name, cpf)
                .flatMap(this::fetchPatientAddress);
    }

    @Override
    public Mono<Patient> findByIdEager(Long patientId) {
        return findById(patientId)
                .flatMap(this::fetchPatientAddress);
    }

    private Mono<Patient> fetchPatientAddress(Patient patient) {
        final var patientId = patient.getId();
        return patientAddressCrudRepository.findPatientAddressByPatientId(patientId)
                .collectList()
                .flatMap(patientAddress -> {
                    patient.setPatientAddress(patientAddress);
                    return Mono.just(patient);
                });
    }
}
