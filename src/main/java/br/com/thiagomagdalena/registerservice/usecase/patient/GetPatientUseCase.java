package br.com.thiagomagdalena.registerservice.usecase.patient;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface GetPatientUseCase extends UseCase<Long, Mono<PatientResponse>> {
}
