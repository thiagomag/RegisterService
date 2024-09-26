package br.com.thiagomagdalena.registerservice.persistance.repository.patient;

import br.com.thiagomagdalena.registerservice.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.registerservice.persistance.repository.BaseReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface PatientCrudRepository extends BaseReactiveCrudRepository<Patient, Long> {

    @Query("SELECT * FROM patient p " +
            "WHERE p.deleted_tmsp is null " +
            "AND ('' = :cpf OR p.cpf = :cpf) " +
            "AND ('' = :name OR LOWER(p.name) LIKE :name)")
    Flux<Patient> findByRequestSearchParams(@Param("name") String name,
                                            @Param("cpf") String cpf);

}