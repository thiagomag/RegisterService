package br.com.thiagomagdalena.registerservice.usecase.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalSearchParams;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Flux;

public interface GetHealthProfessionalsUseCase extends UseCase<HealthProfessionalSearchParams, Flux<HealthProfessionalResponse>> {
}
