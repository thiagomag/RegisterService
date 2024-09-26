package br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional;

import br.com.thiagomagdalena.registerservice.enums.healthprofessional.HealthProfessionalTypeEnum;
import br.com.thiagomagdalena.registerservice.persistance.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Table(name = "health_professional")
public class HealthProfessional extends BaseEntity<Long> {

    @Id
    private Long id;
    private String name;
    private String cpf;
    private String councilRegister;
    private HealthProfessionalTypeEnum healthProfessionalType;
    private String speciality;
    private String telephone;
    private String email;
    @Transient
    private List<HealthProfessionalAddress> address;

}
