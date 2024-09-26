package br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional;

import br.com.thiagomagdalena.registerservice.persistance.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Table(name = "healthprofessional_address")
public class HealthProfessionalAddress extends BaseEntity<Long> {

    @Id
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private Long healthProfessionalId;
}
