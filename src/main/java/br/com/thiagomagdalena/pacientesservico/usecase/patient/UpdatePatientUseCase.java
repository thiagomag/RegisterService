package br.com.thiagomagdalena.pacientesservico.usecase.patient;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.usecase.UseCase;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface UpdatePatientUseCase extends UseCase<Tuple2<Long, PatientRequest>, Mono<PatientResponse>> {
}
