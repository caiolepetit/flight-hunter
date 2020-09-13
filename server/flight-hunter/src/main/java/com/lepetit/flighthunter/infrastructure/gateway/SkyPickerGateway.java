package com.lepetit.flighthunter.infrastructure.gateway;

import com.lepetit.flighthunter.flight.domain.Response;
import com.lepetit.flighthunter.infrastructure.feign.SkyPickerClient;
import com.lepetit.flighthunter.infrastructure.integrator.SkyPickerIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkyPickerGateway implements SkyPickerIntegrator {

    @Autowired
    private SkyPickerClient skyPickerClient;

    @Override
    public Response listFlights(String airportFrom, String airportTo, String currency) {
        return skyPickerClient.listFlights(airportFrom, airportTo, "picky", currency);
    }
}