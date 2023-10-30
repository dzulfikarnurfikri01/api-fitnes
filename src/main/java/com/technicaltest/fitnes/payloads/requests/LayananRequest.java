package com.technicaltest.fitnes.payloads.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LayananRequest {
    private Integer id;
    private String nama;
    private String jadwal;
    private Integer harga;
    private String deskripsi;
}
