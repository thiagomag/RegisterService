package br.com.thiagomagdalena.registerservice.usecase.patient.adapter;

import br.com.thiagomagdalena.registerservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientAddressResponse;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientResponse;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.PatientAddress;
import br.com.thiagomagdalena.registerservice.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientResponseAdapter extends AbstractAdapter<Patient, PatientResponse> {

    public PatientResponseAdapter(JsonUtils jsonUtils) {
        super(PatientResponse.class, jsonUtils);
    }

    @Override
    protected ModelMapper getModelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = super.getModelMapper();

            this.modelMapper.typeMap(Patient.class, PatientResponse.class)
                    .addMappings(mapping -> mapping.using(toAddress()).map(Patient::getPatientAddress, PatientResponse::setPatientAddress));
        }

        return this.modelMapper;
    }

    private Converter<List<PatientAddress>, List<PatientAddressResponse>> toAddress() {
        return context -> context.getSource()
                .stream()
                .map(patientAddress -> PatientAddressResponse.builder()
                        .id(patientAddress.getId())
                        .street(patientAddress.getStreet())
                        .number(patientAddress.getNumber())
                        .complement(patientAddress.getComplement())
                        .neighborhood(patientAddress.getNeighborhood())
                        .city(patientAddress.getCity())
                        .state(patientAddress.getState())
                        .country(patientAddress.getCountry())
                        .zipCode(patientAddress.getZipCode())
                        .build())
                .collect(Collectors.toList());
    }
}
