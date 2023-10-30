package com.technicaltest.fitnes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.fitnes.payloads.responses.LayananResponse;
import com.technicaltest.fitnes.services.LayananService;

@RestController
@RequestMapping("/api")
public class LayananController {
    
    @Autowired
    private LayananService layananService;

    @GetMapping("/layanan")
    public ResponseEntity<?> getLayanan(){
        try {
            LayananResponse response = layananService.getLayanan();
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
