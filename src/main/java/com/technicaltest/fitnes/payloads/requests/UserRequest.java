package com.technicaltest.fitnes.payloads.requests;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;

    @NotEmpty(message = "Email tidak boleh kosong")
    @Email
    private String email;
    private String password;
    private String no_kartu;
    private String cvv;
    private Date exp_date;
    private String nama_pemilik;
    private String reset_password;
    private String token;
    private Date exp_token;
    private Integer is_active;
}
