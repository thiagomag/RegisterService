package br.com.thiagomagdalena.pacientesservico.api.patient;

import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.patient.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.usecase.patient.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final GetPatientsUseCase getPatientsUseCase;
    private final GetPatientUseCase getPatientUseCase;
    private final CreatePatientUseCase createPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientResponse> findAll(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String cpf) {

        final var patientSearchParams = PatientSearchParams.builder()
                .name(name)
                .cpf(cpf)
                .build();

        return getPatientsUseCase.execute(patientSearchParams);
    }

    @GetMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientResponse> findById(@PathVariable Long patientId) {
        return getPatientUseCase.execute(patientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PatientResponse> create(@RequestBody @Valid PatientRequest patientRequest) {
        return createPatientUseCase.execute(patientRequest);
    }

    @PatchMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientResponse> update(@PathVariable Long patientId, @RequestBody PatientRequest patientRequest) {
        return updatePatientUseCase.execute(Tuples.of(patientId, patientRequest));
    }

    @DeleteMapping("/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long patientId) {
        return deletePatientUseCase.execute(patientId);
    }
}
