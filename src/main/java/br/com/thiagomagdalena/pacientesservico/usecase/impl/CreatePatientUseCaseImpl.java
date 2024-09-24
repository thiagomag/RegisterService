package br.com.thiagomagdalena.pacientesservico.usecase.impl;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.PatientAddressRepository;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.CreatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.adapter.PatientAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.adapter.PatientResponseAdapter;
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
    private final PatientAddressRepository patientAddressRepository;

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
        return patientAddressRepository.saveAll(patient.getPatientAddress())
                .then(Mono.just(patient));
    }
}
