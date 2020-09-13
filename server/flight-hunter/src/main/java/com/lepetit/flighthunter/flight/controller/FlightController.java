package com.lepetit.flighthunter.flight.controller;

import com.lepetit.flighthunter.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @CrossOrigin(value = "*")
    @GetMapping(value = "/lowest-price")
    public ResponseEntity<Object> getLowestPriceFlight(@RequestParam(value = "airportFrom", required = false) String airportFrom,
                                                       @RequestParam(value = "airportTo", required = false) String airportTo,
                                                       @RequestParam(value = "currency", required = false) String currency) {
        return ResponseEntity.ok(flightService.getLowestPrice(airportFrom, airportTo, currency));
    }
}