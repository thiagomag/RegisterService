package br.com.thiagomagdalena.pacientesservico.usecase;

import reactor.core.publisher.Mono;

public interface DeletePatientUseCase extends UseCase<Long, Mono<Void>> {
}
