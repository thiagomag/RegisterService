package br.com.thiagomagdalena.registerservice.usecase.healthprofessional;

import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteHealthProfessionalUseCase  extends UseCase<Long, Mono<Void>> {
}
