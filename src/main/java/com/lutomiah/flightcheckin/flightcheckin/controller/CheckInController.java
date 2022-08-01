package com.lutomiah.flightcheckin.flightcheckin.controller;

import com.lutomiah.flightcheckin.flightcheckin.integration.ReservationRESTClient;
import com.lutomiah.flightcheckin.flightcheckin.model.ApiResponse;
import com.lutomiah.flightcheckin.flightcheckin.model.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class CheckInController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckInController.class);

    @Autowired
    ReservationRESTClient reservationRESTClient;

    @RequestMapping(value = "/startCheckin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> startChecking(@RequestBody Map<String, Long> request){
        LOGGER.info("REQUEST {}", request);
        Long id = request.get("reservationId");
        Optional<Reservation> reservation = Optional.ofNullable(reservationRESTClient.findReservation(request.get("reservationId")));

        if (reservation.isPresent()){

            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/completeCheckin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> completeCheckin(@RequestBody Map<String, String> requestMap){
        LOGGER.info("REQUEST PAYLOAD {}", requestMap);
        ApiResponse apiResponse = new ApiResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Id", requestMap.get("id"));
        Long id = Long.valueOf(requestMap.get("Id").trim());

        Optional<Reservation> reservation = Optional.ofNullable(reservationRESTClient.findReservation(id));

        if (reservation.isPresent()){
            LOGGER.info("RESERVATION PRESENT");
            reservationRESTClient.updateReservation(requestMap);

            apiResponse.setResponseCode("00");
            apiResponse.setResponseBody(requestMap);
        }else {
            LOGGER.info("RESERVATION !PRESENT");
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("There is no reservation of id " + id);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
