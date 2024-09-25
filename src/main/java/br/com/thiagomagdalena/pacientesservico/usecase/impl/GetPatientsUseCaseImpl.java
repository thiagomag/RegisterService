package br.com.thiagomagdalena.pacientesservico.usecase.impl;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.GetPatientsUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.adapter.PatientResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GetPatientsUseCaseImpl implements GetPatientsUseCase {

    private final PatientRepository patientRepository;
    private final PatientResponseAdapter patientResponseAdapter;

    @Override
    public Flux<PatientResponse> execute(PatientSearchParams patientSearchParams) {
        return patientRepository.findAll()
                .flatMap(patient -> Flux.just(patientResponseAdapter.adapt(patient)));
    }
}
