package br.com.thiagomagdalena.pacientesservico.usecase.adapter;

import br.com.thiagomagdalena.pacientesservico.adapter.AbstractAdapter;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientAddressRequest;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientRequest;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.PatientAddress;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import br.com.thiagomagdalena.pacientesservico.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

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

    private Converter<PatientAddressRequest, PatientAddress> toAddress() {
        return context -> {
            final var addressRequest = context.getSource();
            return PatientAddress.builder()
                    .street(addressRequest.getStreet())
                    .number(addressRequest.getNumber())
                    .complement(addressRequest.getComplement())
                    .neighborhood(addressRequest.getNeighborhood())
                    .city(addressRequest.getCity())
                    .state(addressRequest.getState())
                    .country(addressRequest.getCountry())
                    .zipCode(addressRequest.getZipCode())
                    .build();
        };
    }
}
