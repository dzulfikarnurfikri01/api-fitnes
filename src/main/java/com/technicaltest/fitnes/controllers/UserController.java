package com.technicaltest.fitnes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technicaltest.fitnes.payloads.requests.UserRequest;
import com.technicaltest.fitnes.payloads.responses.UserResponse;
import com.technicaltest.fitnes.services.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/registrasi")
    public ResponseEntity<?> registrasi(@Valid @RequestBody UserRequest userRequest, Errors errors){
        UserResponse res = new UserResponse();
        if(errors.hasErrors()){
            List<String> err = new ArrayList<>();
            for(ObjectError error : errors.getAllErrors()){
                err.add(error.getDefaultMessage());
                res.setError(err);
            }

            res.setStatus(false);
            res.setData(null);
            res.setMessage("Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(res);
        }

        try {
            UserResponse response = userService.registrasi(userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping("/aktivasi/{id}")
    public ResponseEntity<?> aktivasi(@PathVariable("id") Integer id, @RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.aktivasi(id, userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/cekstatus")
    public ResponseEntity<?> cekStatus(@RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.cekStatus(userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.authentication(userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/lupapassword")
    public ResponseEntity<?> lupapassword(@RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.lupapassword(userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/resetpassword/{id}")
    public ResponseEntity<?> resetpassword(@PathVariable("id") Integer id, @RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.resetpassword(id, userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable("id") Integer id) {
        try {
            UserResponse response = userService.logout(id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @PutMapping("/updateprofile")
    public ResponseEntity<?> updateProfile(@RequestBody UserRequest userRequest){
        try {
            UserResponse response = userService.updateProfile(userRequest);
            return ResponseEntity.status(HttpStatus.OK.value()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
