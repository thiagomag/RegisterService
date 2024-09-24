package br.com.thiagomagdalena.pacientesservico.usecase.adapter;

import br.com.thiagomagdalena.pacientesservico.adapter.AbstractAdapter;
import br.com.thiagomagdalena.pacientesservico.api.dto.PatientResponse;
import br.com.thiagomagdalena.pacientesservico.persistance.domain.Patient;
import br.com.thiagomagdalena.pacientesservico.utils.JsonUtils;

public class PatientResponseAdapter extends AbstractAdapter<Patient, PatientResponse> {

    public PatientResponseAdapter(JsonUtils jsonUtils) {
        super(PatientResponse.class, jsonUtils);
    }
}
