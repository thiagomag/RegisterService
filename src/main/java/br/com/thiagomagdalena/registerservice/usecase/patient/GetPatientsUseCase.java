package br.com.thiagomagdalena.registerservice.usecase.patient;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.registerservice.usecase.UseCase;
import reactor.core.publisher.Flux;

public interface GetPatientsUseCase extends UseCase<PatientSearchParams, Flux<PatientResponse>> {
}
