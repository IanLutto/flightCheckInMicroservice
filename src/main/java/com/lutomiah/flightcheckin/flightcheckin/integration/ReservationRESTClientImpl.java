package com.lutomiah.flightcheckin.flightcheckin.integration;

import com.lutomiah.flightcheckin.flightcheckin.model.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class ReservationRESTClientImpl implements ReservationRESTClient{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTClientImpl.class);

    @Autowired
Environment environment;
    @Override
    public Reservation findReservation(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/flightreservation/findReservation/" + id;
        Reservation reservation = restTemplate.getForObject(url, Reservation.class);

        LOGGER.info("<<<AM HERE>>>");
        LOGGER.info("WHATS INSIDE " + reservation.isCheckedIn());
        return reservation;
    }

    @Override
    public Reservation updateReservation(Map<String, String> request) {
        LOGGER.info("UPDATE PAYLOAD {}", request);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/flightreservation/updateReservation";
        Reservation reservation = restTemplate.postForObject(url,request, Reservation.class);

        return reservation;
    }

}
