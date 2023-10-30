package com.technicaltest.fitnes.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicaltest.fitnes.models.Subscriber;
import com.technicaltest.fitnes.payloads.requests.SubscriberRequest;
import com.technicaltest.fitnes.payloads.responses.SubscriberResponse;
import com.technicaltest.fitnes.repository.SubscriberRepository;

@Service
public class SubscriberService {
    
    @Autowired
    private SubscriberRepository subscriberRepository;

    public SubscriberResponse subcribe(SubscriberRequest subscriberRequest){
        SubscriberResponse response = new SubscriberResponse();

        Subscriber subs = new Subscriber();

        subs.setIduser(subscriberRequest.getIduser());
        subs.setIdlayanan(subscriberRequest.getIdlayanan());
        subs.setIs_active(1);

        subs = subscriberRepository.save(subs);

        response.setStatus(true);
        response.setData(subs);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }

    public SubscriberResponse unsubcribe(SubscriberRequest subscriberRequest){
        SubscriberResponse response = new SubscriberResponse();

        Subscriber subs = subscriberRepository.findById(subscriberRequest.getId()).orElseThrow(() -> {
            throw new NoSuchElementException("Data tidak ditemukan");
        });

       
        subs.setIs_active(0);

        subs = subscriberRepository.save(subs);

        response.setStatus(true);
        response.setData(subs);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }
}
