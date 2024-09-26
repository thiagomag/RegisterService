package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.UpdateHealthProfessionalUseCase;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalAdapter;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalResponseAdapter;
import br.com.thiagomagdalena.registerservice.usecase.patient.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@RequiredArgsConstructor
public class UpdateHealthProfessionalUseCaseImpl implements UpdateHealthProfessionalUseCase {

    private final HealthProfessionalRepository healthProfessionalRepository;
    private final HealthProfessionalAdapter healthProfessionalAdapter;
    private final HealthProfessionalResponseAdapter healthProfessionalResponseAdapter;

    @Override
    public Mono<HealthProfessionalResponse> execute(Tuple2<Long, HealthProfessionalRequest> input) {
        final var healthProfessionalId = input.getT1();
        final var healthProfessionalRequest = input.getT2();

        return healthProfessionalRepository.findById(input.getT1())
                .switchIfEmpty(Mono.defer(() -> {
                    final var msg = String.format("Health Professional with id %s not found", healthProfessionalId);
                    return Mono.error(new PatientNotFoundException(msg));
                }))
                .flatMap(healthProfessional -> {
                    final var healthProfessionalUpdated = healthProfessionalAdapter.adapt(healthProfessionalRequest, healthProfessional);
                    return healthProfessionalRepository.save(healthProfessionalUpdated);
                })
                .map(healthProfessionalResponseAdapter::adapt);
    }
}
