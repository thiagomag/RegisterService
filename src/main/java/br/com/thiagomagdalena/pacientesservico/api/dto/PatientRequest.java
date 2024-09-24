package br.com.thiagomagdalena.pacientesservico.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;
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
public class PatientRequest {

    @NotNull
    private String name;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    private LocalDate dataNascimento;
    private String genero;
    private List<PatientAddressRequest> patientAddressRequest;
    private String telephone;
    @NotNull
    private String email;
    @NotNull
    private String bloodType;
    private String alergies;
    private String preExistingConditions;
}
