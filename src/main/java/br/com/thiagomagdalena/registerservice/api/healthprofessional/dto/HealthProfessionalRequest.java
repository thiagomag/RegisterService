package br.com.thiagomagdalena.registerservice.api.healthprofessional.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HealthProfessionalRequest {

    @NotNull
    private String name;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    private String councilRegister;
    @NotNull
    private String healthProfessionalType;
    private String speciality;
    @NotNull
    private String telephone;
    @NotNull
    private String email;
    private List<HealthProfessionalAddressRequest> address;
}
