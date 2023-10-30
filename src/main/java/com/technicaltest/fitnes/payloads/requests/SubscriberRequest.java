package com.technicaltest.fitnes.payloads.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriberRequest {
    private Integer id;
    private Integer iduser;
    private Integer idlayanan;
    private Integer is_active;
}
