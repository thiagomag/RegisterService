package br.com.thiagomagdalena.pacientesservico.usecase.patient.impl;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.UpdatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.adapter.PatientAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.adapter.PatientResponseAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@RequiredArgsConstructor
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {

    private final PatientAdapter patientAdapter;
    private final PatientResponseAdapter patientResponseAdapter;
    private final PatientRepository patientRepository;

    @Override
    public Mono<PatientResponse> execute(Tuple2<Long, PatientRequest> tuples) {
        final var patientId = tuples.getT1();
        final var patientRequest = tuples.getT2();

        return patientRepository.findById(patientId)
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Patient with id %s not found", patientId);
                    return Mono.error(new PatientNotFoundException(msg));
                }))
                .flatMap(patient -> {
                    final var patientUpdated = patientAdapter.adapt(patientRequest, patient);
                    return patientRepository.save(patientUpdated);
                })
                .map(patientResponseAdapter::adapt);
    }
}
