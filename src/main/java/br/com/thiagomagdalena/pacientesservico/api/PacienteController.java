package br.com.thiagomagdalena.pacientesservico.api;

import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import br.com.thiagomagdalena.pacientesservico.usecase.CreatePatientUseCase;
import br.com.thiagomagdalena.pacientesservico.usecase.UpdatePatientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final CreatePatientUseCase createPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Patient> findAll() {
        return null;
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
