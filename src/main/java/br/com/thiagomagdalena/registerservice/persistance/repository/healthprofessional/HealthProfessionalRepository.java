package br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional;

import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalSearchParams;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessional;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveComposedCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HealthProfessionalRepository extends BaseReactiveComposedCrudRepository<HealthProfessional, Long> {

    Flux<HealthProfessional> findBySearchParams(HealthProfessionalSearchParams healthProfessionalSearchParams);

    Mono<HealthProfessional> findByIdEager(Long healthProfessionalId);
}
