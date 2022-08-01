package com.lutomiah.flightcheckin.flightcheckin.integration;

import com.lutomiah.flightcheckin.flightcheckin.model.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ReservationRESTClient {
    public Reservation findReservation(Long id);

    public Reservation updateReservation(Map<String, String> request);

    }
