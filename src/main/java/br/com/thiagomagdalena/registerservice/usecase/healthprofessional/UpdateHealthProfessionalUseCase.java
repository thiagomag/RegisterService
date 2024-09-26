package br.com.thiagomagdalena.registerservice.usecase.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface UpdateHealthProfessionalUseCase extends UseCase<Tuple2<Long, HealthProfessionalRequest>, Mono<HealthProfessionalResponse>> {
}
