package br.com.thiagomagdalena.pacientesservico.usecase.adapter;

import br.com.thiagomagdalena.pacientesservico.adapter.AbstractAdapter;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientAddressResponse;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.PatientAddress;
import br.com.thiagomagdalena.pacientesservico.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

    private Converter<PatientAddress, PatientAddressResponse> toAddress() {
        return context -> {
            final var address = context.getSource();
            return PatientAddressResponse.builder()
                    .street(address.getStreet())
                    .number(address.getNumber())
                    .complement(address.getComplement())
                    .neighborhood(address.getNeighborhood())
                    .city(address.getCity())
                    .state(address.getState())
                    .country(address.getCountry())
                    .zipCode(address.getZipCode())
                    .build();
        };
    }
}
