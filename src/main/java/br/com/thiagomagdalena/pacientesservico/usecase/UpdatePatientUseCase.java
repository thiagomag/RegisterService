package br.com.thiagomagdalena.pacientesservico.usecase;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface UpdatePatientUseCase extends UseCase<Tuple2<Long, PatientRequest>, Mono<PatientResponse>> {
}
