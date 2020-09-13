package com.lepetit.flighthunter.flight.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lepetit.flighthunter.flight.domain.FlightDetails;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class FlightDTO {
    @JsonProperty(value = "flight")
    private FlightDetails flightDetails;
}