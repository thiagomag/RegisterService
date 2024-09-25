package br.com.thiagomagdalena.pacientesservico.usecase;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientSearchParams;
import reactor.core.publisher.Flux;

public interface GetPatientsUseCase extends UseCase<PatientSearchParams, Flux<PatientResponse>> {
}
