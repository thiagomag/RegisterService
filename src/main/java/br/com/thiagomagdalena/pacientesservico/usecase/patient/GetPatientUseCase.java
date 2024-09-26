package br.com.thiagomagdalena.pacientesservico.usecase.patient;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.usecase.UseCase;
import reactor.core.publisher.Mono;

public interface GetPatientUseCase extends UseCase<Long, Mono<PatientResponse>> {
}
