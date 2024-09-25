package br.com.thiagomagdalena.pacientesservico.persistance.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

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
@Table(name = "pacientes")
public class Patient extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String alergies;
    private String preExistingConditions;
    @Transient
    private List<Consultation> consultations;

    public List<PatientAddress> getPatientAddress() {
        if (patientAddress == null) {
            patientAddress = new ArrayList<>();
        }
        return patientAddress;
    }

    public List<Consultation> getConsultations() {
        if (consultations == null) {
            consultations = new ArrayList<>();
        }
        return consultations;
    }
}
