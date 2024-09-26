package br.com.thiagomagdalena.registerservice.usecase.patient.impl;

import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.registerservice.usecase.patient.GetPatientsUseCase;
import br.com.thiagomagdalena.registerservice.usecase.patient.adapter.PatientResponseAdapter;
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
        return patientRepository.findBySearchParams(patientSearchParams)
                .flatMap(patient -> Flux.just(patientResponseAdapter.adapt(patient)));
    }
}
