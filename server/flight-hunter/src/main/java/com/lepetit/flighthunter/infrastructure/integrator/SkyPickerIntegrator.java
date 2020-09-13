package com.lepetit.flighthunter.infrastructure.integrator;

import com.lepetit.flighthunter.flight.domain.Response;

public interface SkyPickerIntegrator {
    Response listFlights(String airportFrom, String airportTo, String currency);
}