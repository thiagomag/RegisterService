package br.com.thiagomagdalena.pacientesservico.usecase.impl;

import br.com.thiagomagdalena.pacientesservico.persistance.repository.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.DeletePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.exception.PatientNotFoundException;
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
                .flatMap(patientRepository::softDelete);
    }
}
