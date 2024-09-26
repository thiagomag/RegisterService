package br.com.thiagomagdalena.pacientesservico.usecase.patient.impl;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.patient.PatientAddressCrudRepository;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.CreatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.adapter.PatientAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.adapter.PatientResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatePatientUseCaseImpl implements CreatePatientUseCase {

    private final PatientAdapter patientAdapter;
    private final PatientResponseAdapter patientResponseAdapter;
    private final PatientRepository patientRepository;
    private final PatientAddressCrudRepository patientAddressCrudRepository;

    @Override
    public Mono<PatientResponse> execute(PatientRequest patientRequest) {
        final var patient = patientAdapter.adapt(patientRequest);
        return patientRepository.save(patient)
                .flatMap(this::addPatientIdAndSavePatientAddress)
                .map(patientResponseAdapter::adapt);
    }

    private Mono<Patient> addPatientIdAndSavePatientAddress(Patient patient) {
        Optional.ofNullable(patient.getPatientAddress())
                .map(patientAddresses -> patientAddresses.stream().peek(patientAddress -> patientAddress.setPatientId(patient.getId())).collect(Collectors.toList()));
        return patientAddressCrudRepository.saveAll(patient.getPatientAddress())
                .then(Mono.just(patient));
    }
}
