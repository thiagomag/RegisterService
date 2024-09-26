package br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.impl;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessional;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalAddressCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalCrudRepository;
import br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional.HealthProfessionalRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class HealthProfessionalRepositoryImpl implements HealthProfessionalRepository {

    private final HealthProfessionalCrudRepository healthProfessionalCrudRepository;
    private final HealthProfessionalAddressCrudRepository healthProfessionalAddressCrudRepository;


    public HealthProfessionalRepositoryImpl(HealthProfessionalCrudRepository healthProfessionalCrudRepository,
                                            HealthProfessionalAddressCrudRepository healthProfessionalAddressCrudRepository) {
        this.healthProfessionalCrudRepository = healthProfessionalCrudRepository;
        this.healthProfessionalAddressCrudRepository = healthProfessionalAddressCrudRepository;
    }

    @Override
    public BaseReactiveCrudRepository<HealthProfessional, Long> getRepository() {
        return healthProfessionalCrudRepository;
    }

    @Override
    public Flux<HealthProfessional> findBySearchParams(HealthProfessionalSearchParams healthProfessionalSearchParams) {
        final var name = Optional.ofNullable(healthProfessionalSearchParams.getName())
                .map(String::toLowerCase)
                .map(s -> "%" + s + "%")
                .orElse("");
        final var cpf = Optional.ofNullable(healthProfessionalSearchParams.getCpf()).orElse("");
        final var councilRegister = Optional.ofNullable(healthProfessionalSearchParams.getCouncilRegister()).orElse("");
        return healthProfessionalCrudRepository.findByRequestSearchParams(name, cpf, councilRegister)
                .flatMap(this::fetchPatientAddress);
    }

    @Override
    public Mono<HealthProfessional> findByIdEager(Long patientId) {
        return findById(patientId)
                .flatMap(this::fetchPatientAddress);
    }

    private Mono<HealthProfessional> fetchPatientAddress(HealthProfessional healthProfessional) {
        final var healthProfessionalId = healthProfessional.getId();
        return healthProfessionalAddressCrudRepository.findHealthProfessionalAddressByHealthProfessionalId(healthProfessionalId)
                .collectList()
                .flatMap(healthProfessionalAddress -> {
                    healthProfessional.setAddress(healthProfessionalAddress);
                    return Mono.just(healthProfessional);
                });
    }
}
