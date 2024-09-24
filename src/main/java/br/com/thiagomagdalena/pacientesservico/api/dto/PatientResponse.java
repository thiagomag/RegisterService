package br.com.thiagomagdalena.pacientesservico.api.dto;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.Consultation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class PatientResponse {

    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String gender;
    private PatientAddressResponse patientAddress;
    private String telephone;
    private String email;
    private String bloodType;
    private String alergies;
    private String preExistingConditions;
    private List<Consultation> consultations;
}
