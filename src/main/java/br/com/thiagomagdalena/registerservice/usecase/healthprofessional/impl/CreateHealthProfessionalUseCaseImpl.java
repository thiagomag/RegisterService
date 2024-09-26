package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.CreateHealthProfessionalUseCase;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalAdapter;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateHealthProfessionalUseCaseImpl implements CreateHealthProfessionalUseCase {

    private final HealthProfessionalRepository healthProfessionalRepository;
    private final HealthProfessionalAdapter healthProfessionalAdapter;
    private final HealthProfessionalResponseAdapter healthProfessionalResponseAdapter;

    @Override
    public Mono<HealthProfessionalResponse> execute(HealthProfessionalRequest healthProfessionalRequest) {
        final var healtProfessional = healthProfessionalAdapter.adapt(healthProfessionalRequest);

        return healthProfessionalRepository.save(healtProfessional)
                .map(healthProfessionalResponseAdapter::adapt);
    }
}
