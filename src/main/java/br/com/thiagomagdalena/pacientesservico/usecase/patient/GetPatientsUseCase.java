package br.com.thiagomagdalena.pacientesservico.usecase.patient;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.usecase.UseCase;
import reactor.core.publisher.Flux;

public interface GetPatientsUseCase extends UseCase<PatientSearchParams, Flux<PatientResponse>> {
}
