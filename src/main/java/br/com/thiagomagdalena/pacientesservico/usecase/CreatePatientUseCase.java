package br.com.thiagomagdalena.pacientesservico.usecase;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import reactor.core.publisher.Mono;

public interface CreatePatientUseCase extends UseCase<PatientRequest, Mono<PatientResponse>>{
}
