package br.com.thiagomagdalena.registerservice.persistance.repository.healthprofessional;

import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessional;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface HealthProfessionalCrudRepository extends BaseReactiveCrudRepository<HealthProfessional, Long> {

    @Query("SELECT * FROM health_professional hp " +
            "WHERE hp.deleted_tmsp is null " +
            "AND ('' = :cpf OR hp.cpf = :cpf) " +
            "AND ('' = :name OR LOWER(hp.name) LIKE :name)" +
            "AND ('' = :councilRegister OR hp.council_register = :councilRegister)")
    Flux<HealthProfessional> findByRequestSearchParams(@Param("name") String name,
                                                       @Param("cpf") String cpf,
                                                       @Param("councilRegister") String councilRegister);

}