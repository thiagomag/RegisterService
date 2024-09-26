package br.com.thiagomagdalena.registerservice.usecase.patient;

import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface DeletePatientUseCase extends UseCase<Long, Mono<Void>> {
}
