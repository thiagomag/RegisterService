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
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
    private String endereco;
    private String telefone;
    private String email;
    private String tipoSanguineo;
    private String alergias;
    private String condicoesPreexistentes;
    private List<Consultation> consultations;
}
