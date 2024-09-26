package br.com.thiagomagdalena.registerservice.enums.healthprofessional;

import br.com.thiagomagdalena.registerservice.enums.EnumSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum HealthProfessionalTypeEnum implements EnumSerializable {

    PHYSICIAN("physician"),
    NURSE("nurse"),
    PHARMACIST("pharmacist"),
    DENTIST("dentist"),
    PHYSIOTHERAPIST("physiotherapist"),
    OCCUPATIONAL_THERAPIST("occupational_therapist"),
    PSYCHOLOGIST("psychologist"),
    PSYCHIATRIST("psychiatrist"),
    SURGEON("surgeon"),
    CHIROPRACTOR("chiropractor"),
    DIETITIAN("dietitian"),
    NUTRITIONIST("nutritionist"),
    RADIOLOGIST("radiologist"),
    SPEECH_LANGUAGE_PATHOLOGIST("speech_language_pathologist"),
    PODIATRIST("podiatrist"),
    OPTOMETRIST("optometrist"),
    RESPIRATORY_THERAPIST("respiratory_therapist"),
    SOCIAL_WORKER("social_worker"),
    AUDIOLOGIST("audiologist"),
    EMT_PARAMEDIC("emt_paramedic");

    private final String value;

    public static HealthProfessionalTypeEnum findBy(final String value) {
        return Stream.of(HealthProfessionalTypeEnum.values())
                .filter(item -> item.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
