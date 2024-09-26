package br.com.thiagomagdalena.registerservice.api.patient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<PatientAddressResponse> patientAddress;
    private String telephone;
    private String email;
    private String bloodType;
    private String allergies;
    private String preExistingConditions;

    public List<PatientAddressResponse> getPatientAddress() {
        if (patientAddress == null) {
            patientAddress = new ArrayList<>();
        }
        return patientAddress;
    }
}
