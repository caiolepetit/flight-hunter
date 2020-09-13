package com.lepetit.flighthunter.flight.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDetails {
    private String flyFrom;
    private String cityFrom;
    private Country countryFrom;
    private String flyTo;
    private String cityTo;
    private Country countryTo;
    private BigDecimal price;
}