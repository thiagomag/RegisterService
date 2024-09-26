package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.GetHealthProfessionalUseCase;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalResponseAdapter;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.exception.HealthProfessionalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetHealthProfessionalUseCaseImpl implements GetHealthProfessionalUseCase {
    private final HealthProfessionalRepository healthProfessionalRepository;
    private final HealthProfessionalResponseAdapter healthProfessionalResponseAdapter;


    @Override
    public Mono<HealthProfessionalResponse> execute(Long healthProfessionalId) {
        return healthProfessionalRepository.findById(healthProfessionalId)
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Health Professional with id %s not found", healthProfessionalId);
                    return Mono.error(new HealthProfessionalNotFoundException(msg));
                }))
                .map(healthProfessionalResponseAdapter::adapt);
    }
}
