package br.com.thiagomagdalena.registerservice.usecase.healthprofessional.adapter;

import br.com.thiagomagdalena.registerservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalAddressRequest;
import br.com.thiagomagdalena.registerservice.api.healthprofessional.dto.HealthProfessionalRequest;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessional;
import br.com.thiagomagdalena.registerservice.persistance.domain.healthprofessional.HealthProfessionalAddress;
import br.com.thiagomagdalena.registerservice.utils.JsonUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthProfessionalAdapter extends AbstractAdapter<HealthProfessionalRequest, HealthProfessional> {

    public HealthProfessionalAdapter(JsonUtils jsonUtils) {
        super(HealthProfessional.class, jsonUtils);
    }

    @Override
    protected ModelMapper getModelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = super.getModelMapper();

            this.modelMapper.typeMap(HealthProfessionalRequest.class, HealthProfessional.class)
                    .addMappings(mapping -> mapping.using(toAddress()).map(HealthProfessionalRequest::getAddress, HealthProfessional::setAddress));
        }

        return this.modelMapper;
    }

    private Converter<List<HealthProfessionalAddressRequest>, List<HealthProfessionalAddress>> toAddress() {
        return context -> context.getSource()
                .stream()
                .map(healthProfessionalAddressRequest -> HealthProfessionalAddress.builder()
                        .street(healthProfessionalAddressRequest.getStreet())
                        .number(healthProfessionalAddressRequest.getNumber())
                        .complement(healthProfessionalAddressRequest.getComplement())
                        .neighborhood(healthProfessionalAddressRequest.getNeighborhood())
                        .city(healthProfessionalAddressRequest.getCity())
                        .state(healthProfessionalAddressRequest.getState())
                        .country(healthProfessionalAddressRequest.getCountry())
                        .zipCode(healthProfessionalAddressRequest.getZipCode())
                        .build())
                .collect(Collectors.toList());
    }
}
