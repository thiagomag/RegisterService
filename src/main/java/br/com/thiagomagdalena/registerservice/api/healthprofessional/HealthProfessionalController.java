package br.com.thiagomagdalena.registerservice.api.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalSearchParams;
import br.com.thiagomagdalena.registerservice.usecase.healthprofessional.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/v1/health-professional")
@RequiredArgsConstructor
public class HealthProfessionalController {

    private final GetHealthProfessionalsUseCase getHealthProfessionalsUseCase;
    private final GetHealthProfessionalUseCase getHealthProfessionalUseCase;
    private final CreateHealthProfessionalUseCase createHealthProfessionalUseCase;
    private final UpdateHealthProfessionalUseCase updateHealthProfessionalUseCase;
    private final DeleteHealthProfessionalUseCase deleteHealthProfessionalUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<HealthProfessionalResponse> findAll(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String cpf,
                                                    @RequestParam(required = false) String councilRegister) {

        final var healthProfessionalSearchParams = HealthProfessionalSearchParams.builder()
                .name(name)
                .cpf(cpf)
                .councilRegister(councilRegister)
                .build();

        return getHealthProfessionalsUseCase.execute(healthProfessionalSearchParams);
    }

    @GetMapping("/{healthProfessionalId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<HealthProfessionalResponse> findById(@PathVariable Long healthProfessionalId) {
        return getHealthProfessionalUseCase.execute(healthProfessionalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public Mono<HealthProfessionalResponse> create(@RequestBody @Valid HealthProfessionalRequest healthProfessionalRequest) {
        return createHealthProfessionalUseCase.execute(healthProfessionalRequest);
    }

    @PatchMapping("/{healthProfessionalId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<HealthProfessionalResponse> update(@PathVariable Long healthProfessionalId, @RequestBody HealthProfessionalRequest healthProfessionalRequest) {
        return updateHealthProfessionalUseCase.execute(Tuples.of(healthProfessionalId, healthProfessionalRequest));
    }

    @DeleteMapping("/{healthProfessionalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long healthProfessionalId) {
        return deleteHealthProfessionalUseCase.execute(healthProfessionalId);
    }
}
