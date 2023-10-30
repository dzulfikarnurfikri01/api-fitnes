package com.technicaltest.fitnes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicaltest.fitnes.models.Layanan;
import com.technicaltest.fitnes.payloads.responses.LayananResponse;
import com.technicaltest.fitnes.repository.LayananRepo;

@Service
public class LayananService {
    
    @Autowired
    private LayananRepo layananRepo;

    public LayananResponse getLayanan(){
        LayananResponse response = new LayananResponse();

        Iterable<Layanan> layanan = layananRepo.findAll();

        response.setStatus(true);
        response.setData(layanan);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }

}
