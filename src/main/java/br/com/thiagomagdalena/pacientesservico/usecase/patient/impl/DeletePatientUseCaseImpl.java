package br.com.thiagomagdalena.pacientesservico.usecase.patient.impl;

import br.com.thiagomagdalena.pacientesservico.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.DeletePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeletePatientUseCaseImpl implements DeletePatientUseCase {

    private final PatientRepository patientRepository;

    @Override
    public Mono<Void> execute(Long patientId) {
        return patientRepository.findById(patientId)
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Patient with id %s not found", patientId);
                    return Mono.error(new PatientNotFoundException(msg));
                }))
                .flatMap(patientRepository::softDelete)
                .then();
    }
}
