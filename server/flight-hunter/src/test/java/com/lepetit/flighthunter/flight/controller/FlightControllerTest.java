package com.lepetit.flighthunter.flight.controller;

import com.lepetit.flighthunter.flight.domain.FlightDetails;
import com.lepetit.flighthunter.flight.domain.dto.FlightDTO;
import com.lepetit.flighthunter.flight.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    public void getLowestFlightPrice_whenAirportFromGRUAndToOPOAndCurrencyBRL_thenStatus200 () throws Exception {
        when(flightService.getLowestPrice("GRU", "OPO", "BRL")).thenReturn(FlightDTO.builder().flightDetails(FlightDetails.builder().cityFrom("São Paulo").build()).build());

        mockMvc.perform(get("/flights/lowest-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("airportFrom", "GRU")
                        .param("airportTo", "OPO")
                        .param("currency", "BRL"))
                .andExpect(status().isOk());
    }

    @Test
    public void getLowestFlightPrice_whenAirportFromIsWrong_thenStatus404 () throws Exception {
        when(flightService.getLowestPrice("AAA", "OPO", "BRL")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum voo"));

        mockMvc.perform(get("/flights/lowest-price")
                .contentType(MediaType.APPLICATION_JSON)
                .param("airportFrom", "AAA")
                .param("airportTo", "OPO")
                .param("currency", "BRL"))
                .andExpect(status().isNotFound());
    }
}