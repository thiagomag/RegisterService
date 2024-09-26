package br.com.thiagomagdalena.registerservice.usecase.patient.impl;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.registerservice.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.registerservice.usecase.patient.GetPatientUseCase;
import br.com.thiagomagdalena.registerservice.usecase.patient.adapter.PatientResponseAdapter;
import br.com.thiagomagdalena.registerservice.usecase.patient.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetPatientUseCaseImpl implements GetPatientUseCase {

    private final PatientRepository patientRepository;
    private final PatientResponseAdapter patientResponseAdapter;

    @Override
    public Mono<PatientResponse> execute(Long patientId) {
        return patientRepository.findByIdEager(patientId)
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Patient with id %s not found", patientId);
                    return Mono.error(new PatientNotFoundException(msg));
                }))
                .map(patientResponseAdapter::adapt);
    }
}
