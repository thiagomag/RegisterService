package br.com.thiagomagdalena.registerservice.api.healthprofessional.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

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
public class HealthProfessionalResponse {

    private String id;
    private String name;
    private String cpf;
    private String councilRegister;
    private String healthProfessionalType;
    private String speciality;
    private String telephone;
    private String email;
    private List<HealthProfessionalAddressResponse> address;
}
