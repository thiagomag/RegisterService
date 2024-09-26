package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter;

import br.com.thiagomagdalena.registerservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalAddressResponse;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalResponse;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessional;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessionalAddress;
import br.com.thiagomagdalena.registerservice.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthProfessionalResponseAdapter extends AbstractAdapter<HealthProfessional, HealthProfessionalResponse> {

    public HealthProfessionalResponseAdapter(JsonUtils jsonUtils) {
        super(HealthProfessionalResponse.class, jsonUtils);
    }

    @Override
    protected ModelMapper getModelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = super.getModelMapper();

            this.modelMapper.typeMap(HealthProfessional.class, HealthProfessionalResponse.class)
                    .addMappings(mapping -> mapping.using(toAddress()).map(HealthProfessional::getAddress, HealthProfessionalResponse::setAddress));
        }

        return this.modelMapper;
    }

    private Converter<List<HealthProfessionalAddress>, List<HealthProfessionalAddressResponse>> toAddress() {
        return context -> context.getSource()
                .stream()
                .map(healthProfessionalAddress -> HealthProfessionalAddressResponse.builder()
                        .id(healthProfessionalAddress.getId())
                        .street(healthProfessionalAddress.getStreet())
                        .number(healthProfessionalAddress.getNumber())
                        .complement(healthProfessionalAddress.getComplement())
                        .neighborhood(healthProfessionalAddress.getNeighborhood())
                        .city(healthProfessionalAddress.getCity())
                        .state(healthProfessionalAddress.getState())
                        .country(healthProfessionalAddress.getCountry())
                        .zipCode(healthProfessionalAddress.getZipCode())
                        .build())
                .collect(Collectors.toList());
    }
}