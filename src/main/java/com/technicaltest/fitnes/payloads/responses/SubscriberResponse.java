package com.technicaltest.fitnes.payloads.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriberResponse {
    private Boolean status;
    private Object data;
    private String message;
    private List<String> error = new ArrayList<>();
}
