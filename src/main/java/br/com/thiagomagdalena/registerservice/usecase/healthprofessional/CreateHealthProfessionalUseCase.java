package br.com.thiagomagdalena.registerservice.usecase.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface CreateHealthProfessionalUseCase extends UseCase<HealthProfessionalRequest, Mono<HealthProfessionalResponse>> {
}
