package com.lepetit.flighthunter.infrastructure.feign;

import com.lepetit.flighthunter.FeignConfig;
import com.lepetit.flighthunter.flight.domain.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sky-picker", url = "${rest.sky-picker.url}", configuration = FeignConfig.class)
public interface SkyPickerClient {
    @GetMapping(value = "/flights")
    Response listFlights(@RequestParam(value = "flyFrom") String airportFrom,
                         @RequestParam(value = "to") String airportTo,
                         @RequestParam(value = "partner") String partner,
                         @RequestParam(value = "curr") String currency);
}
