package br.com.thiagomagdalena.registerservice.usecase.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface GetHealthProfessionalUseCase extends UseCase<Long, Mono<HealthProfessionalResponse>> {
}
