package br.com.thiagomagdalena.registerservice.usecase.patient.adapter;

import br.com.thiagomagdalena.registerservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientAddressRequest;
import br.com.thiagomagdalena.registerservice.api.patient.dto.PatientRequest;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.PatientAddress;
import br.com.thiagomagdalena.registerservice.persistance.domain.patient.Patient;
import br.com.thiagomagdalena.registerservice.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientAdapter extends AbstractAdapter<PatientRequest, Patient> {

    public PatientAdapter(JsonUtils jsonUtils) {
        super(Patient.class, jsonUtils);
    }

    @Override
    protected ModelMapper getModelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = super.getModelMapper();

            this.modelMapper.typeMap(PatientRequest.class, Patient.class)
                    .addMappings(mapping -> mapping.using(toAddress()).map(PatientRequest::getPatientAddressRequest, Patient::setPatientAddress));
        }

        return this.modelMapper;
    }

    private Converter<List<PatientAddressRequest>, List<PatientAddress>> toAddress() {
        return context -> context.getSource()
                .stream()
                .map(patientAddressRequest -> PatientAddress.builder()
                        .street(patientAddressRequest.getStreet())
                        .number(patientAddressRequest.getNumber())
                        .complement(patientAddressRequest.getComplement())
                        .neighborhood(patientAddressRequest.getNeighborhood())
                        .city(patientAddressRequest.getCity())
                        .state(patientAddressRequest.getState())
                        .country(patientAddressRequest.getCountry())
                        .zipCode(patientAddressRequest.getZipCode())
                        .build())
                .collect(Collectors.toList());
    }
}
