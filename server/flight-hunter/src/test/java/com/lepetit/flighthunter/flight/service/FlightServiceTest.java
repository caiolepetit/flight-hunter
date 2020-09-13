package com.lepetit.flighthunter.flight.service;

import com.lepetit.flighthunter.flight.domain.FlightDetails;
import com.lepetit.flighthunter.flight.domain.Response;
import com.lepetit.flighthunter.flight.domain.dto.FlightDTO;
import com.lepetit.flighthunter.infrastructure.integrator.SkyPickerIntegrator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    public String airportFrom;
    public String airportTo;
    public String currency;

    @Mock
    private SkyPickerIntegrator skyPickerIntegrator;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        this.airportFrom = "GRU";
        this.airportTo = "OPO";
        this.currency = "BRL";
    }

    @Test
    void resultedFlightsSendAllArguments() {
        List<FlightDetails> flights = new ArrayList<>();
        flights.add(FlightDetails.builder()
                .cityFrom("São Paulo")
                .flyFrom("GRU")
                .cityTo("Porto")
                .flyTo("OPO")
                .build());
        Response expectedResponse = Response.builder()
                                    .flightDetails(flights)
                                    .build();

        when(skyPickerIntegrator.listFlights(this.airportFrom, this.airportTo, this.currency)).thenReturn(expectedResponse);

        List<FlightDetails> resultFlights = flightService.listFlights(this.airportFrom, this.airportTo, this.currency);

        assertEquals(resultFlights, expectedResponse.getFlightDetails());
    }

    @Test
    void getLowestPriceReturningOk() {
        List<FlightDetails> flights = new ArrayList<>();
        flights.add(FlightDetails.builder()
                .cityFrom("São Paulo")
                .flyFrom("GRU")
                .cityTo("Porto")
                .flyTo("OPO")
                .price(new BigDecimal(1800))
                .build());
        flights.add(FlightDetails.builder()
                .cityFrom("São Paulo")
                .flyFrom("GRU")
                .cityTo("Porto")
                .flyTo("OPO")
                .price(new BigDecimal(1000))
                .build());
        Response expectedResponse = Response.builder()
                .flightDetails(flights)
                .build();

        when(skyPickerIntegrator.listFlights(this.airportFrom, this.airportTo, this.currency)).thenReturn(expectedResponse);

        FlightDTO resultFlight = flightService.getLowestPrice(this.airportFrom, this.airportTo, this.currency);

        assertEquals(resultFlight.getFlightDetails().getPrice(), new BigDecimal(1000));
    }

    @Test
    void resultedExceptionWhenSendAirportToWrong() {
        when(skyPickerIntegrator.listFlights(this.airportFrom, "AAA", this.currency)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            flightService.getLowestPrice(this.airportFrom, "AAA", this.currency);
        });
    }
}