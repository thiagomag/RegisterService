package br.com.thiagomagdalena.pacientesservico.usecase.impl;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.UpdatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.adapter.PatientAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.adapter.PatientResponseAdapter;
import br.com.thiagomagdalena.pacientesservico.usecase.exception.PatientNotFoundException;
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
