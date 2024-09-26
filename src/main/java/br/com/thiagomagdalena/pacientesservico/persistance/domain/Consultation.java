package br.com.thiagomagdalena.pacientesservico.persistance.domain;

import br.com.thiagomagdalena.pacientesservico.persistance.domain.patient.Patient;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Table(name = "consulta")
public class Consultation extends BaseEntity<Long> {

    @Id
    private Long id;
    private LocalDateTime date;
    private String professionalResponsible;
    private String diagnostic;
    private String treatment;
    private String observations;
    private Long patientId;
    @Transient
    private Patient patient;
}