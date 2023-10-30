package com.technicaltest.fitnes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.fitnes.payloads.requests.SubscriberRequest;
import com.technicaltest.fitnes.payloads.responses.SubscriberResponse;
import com.technicaltest.fitnes.services.SubscriberService;

@RestController
@RequestMapping("/api")
public class SubscriberController {
    
    @Autowired
    private SubscriberService subscriberService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriberRequest subscriberRequest){
        try {
            SubscriberResponse response = subscriberService.subcribe(subscriberRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<?> unsubscribe(@RequestBody SubscriberRequest subscriberRequest){
        try {
            SubscriberResponse response = subscriberService.unsubcribe(subscriberRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
