package com.technicaltest.fitnes.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer id;

    @Column(length = 100)
    private String nama;

    @Column(length = 100)
    private String email;

    private String password;

    @Column(length = 50)
    private String no_kartu;

    private String cvv;

    @Temporal(TemporalType.DATE)
    private Date exp_date;

    @Column(length = 100)
    private String nama_pemilik;

    private String reset_password;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Long exp_token;

    @Column(length = 1)
    private Integer is_active;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
