package com.lepetit.flighthunter.flight.service;

import com.lepetit.flighthunter.flight.domain.FlightDetails;
import com.lepetit.flighthunter.flight.domain.dto.FlightDTO;
import com.lepetit.flighthunter.infrastructure.integrator.SkyPickerIntegrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private SkyPickerIntegrator skyPickerIntegrator;

    public List<FlightDetails> listFlights(String airportFrom, String airportTo, String currency) {
        return skyPickerIntegrator.listFlights(airportFrom, airportTo, currency).getFlightDetails();
    }

    public FlightDTO getLowestPrice(String airportFrom, String airportTo, String currency) {
        List<FlightDetails> flightDetails = listFlights(airportFrom, airportTo, currency);
        return FlightDTO.builder()
                .flightDetails(flightDetails.stream()
                                .min(Comparator.comparing(FlightDetails::getPrice))
                                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum voo!")))
                .build();
    }
}