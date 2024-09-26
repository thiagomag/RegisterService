package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.DeleteHealthProfessionalUseCase;
import br.com.thiagomagdalena.registerservice.usecase.patient.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteHealthProfessionalUseCaseImpl implements DeleteHealthProfessionalUseCase {

    private final HealthProfessionalRepository healthProfessionalRepository;

    @Override
    public Mono<Void> execute(Long healthProfessionalId) {
        return healthProfessionalRepository.findById(healthProfessionalId)
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Health Professional with id %s not found", healthProfessionalId);
                    return Mono.error(new PatientNotFoundException(msg));
                }))
                .flatMap(healthProfessionalRepository::softDelete)
                .then();
    }


}
