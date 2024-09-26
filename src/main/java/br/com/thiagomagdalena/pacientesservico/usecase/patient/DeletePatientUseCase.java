package br.com.thiagomagdalena.pacientesservico.usecase.patient;

import br.com.thiagomagdalena.pacientesservico.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface DeletePatientUseCase extends UseCase<Long, Mono<Void>> {
}
