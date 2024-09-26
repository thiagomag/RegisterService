package br.com.thiagomagdalena.pacientesservico.usecase.patient.impl;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.persistance.repository.patient.PatientRepository;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.GetPatientsUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.adapter.PatientResponseAdapter;
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
