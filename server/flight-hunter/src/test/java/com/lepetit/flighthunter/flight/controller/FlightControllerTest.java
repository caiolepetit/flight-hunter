package com.lepetit.flighthunter.flight.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLowestFlightPrice_whenAirportFromGRUAndToOPOAndCurrencyBRL_thenStatus200 () throws Exception {
        mockMvc.perform(get("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("airportFrom", "GRU")
                        .param("airportTo", "OPO")
                        .param("currency", "BRL"))
                .andExpect(status().isOk());
    }
}