package br.com.thiagomagdalena.pacientesservico.api;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientSearchParams;
import br.com.thiagomagdalena.pacientesservico.usecase.CreatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.DeletePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.GetPatientsUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.UpdatePatientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final GetPatientsUseCase getPatientsUseCase;
    private final CreatePatientUseCase createPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientResponse> findAll(@RequestParam(required = false) String nome,
                                         @RequestParam(required = false) String cpf) {

        final var patientSearchParams = PatientSearchParams.builder()
                .name(nome)
                .cpf(cpf)
                .build();

        return getPatientsUseCase.execute(patientSearchParams);
    }

    @GetMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientResponse> findById(@PathVariable Long patientId) {
        return null;
    }

    @GetMapping("/nome/{patientName}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientResponse> findByName(@PathVariable String patientName) {
        return null;
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
        return null;
    }
}
