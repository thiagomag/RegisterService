package br.com.thiagomagdalena.registerservice.persistance.domain.patient;

import br.com.thiagomagdalena.registerservice.persistance.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Table(name = "patient")
public class Patient extends BaseEntity<Long> {

    @Id
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String gender;
    @Transient
    private List<PatientAddress> patientAddress;
    private String telephone;
    private String email;
    private String bloodType;
    private String allergies;
    private String preExistingConditions;

    public List<PatientAddress> getPatientAddress() {
        if (patientAddress == null) {
            patientAddress = new ArrayList<>();
        }
        return patientAddress;
    }
}
