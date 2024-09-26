package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.GetHealthProfessionalsUseCase;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter.HealthProfessionalResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetHealthProfessionalsUseCaseImpl implements GetHealthProfessionalsUseCase {

    private final HealthProfessionalRepository healthProfessionalRepository;
    private final HealthProfessionalResponseAdapter healthProfessionalResponseAdapter;

    @Override
    public Flux<HealthProfessionalResponse> execute(HealthProfessionalSearchParams healthProfessionalSearchParams) {
        return healthProfessionalRepository.findBySearchParams(healthProfessionalSearchParams)
                .map(healthProfessionalResponseAdapter::adapt);
    }
}
