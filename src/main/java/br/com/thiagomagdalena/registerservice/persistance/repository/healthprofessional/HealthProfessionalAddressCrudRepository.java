package br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional;

import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessionalAddress;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface HealthProfessionalAddressCrudRepository extends BaseReactiveCrudRepository<HealthProfessionalAddress, Long> {

    Flux<HealthProfessionalAddress> findHealthProfessionalAddressByHealthProfessionalId(Long healthProfessionalId);

}
