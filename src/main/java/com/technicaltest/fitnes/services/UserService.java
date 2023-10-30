package com.technicaltest.fitnes.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicaltest.fitnes.models.Users;
import com.technicaltest.fitnes.payloads.requests.UserRequest;
import com.technicaltest.fitnes.payloads.responses.UserResponse;
import com.technicaltest.fitnes.repository.UserRepository;
import com.technicaltest.fitnes.security.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public UserResponse registrasi(UserRequest users){
        UserResponse response = new UserResponse();

        if(userRepository.existsByEmail(users.getEmail())){
            List<String> err = new ArrayList<>();
            err.add("Email sudah terdaftar");
            response.setStatus(false);
            response.setMessage("Terjadi kesalahan");
            response.setError(err);
            response.setData(null);
            return response;
        }


        Users user = new Users();

        String pwdHash = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());

        user.setNama(users.getNama());
        user.setEmail(users.getEmail());
        user.setPassword(pwdHash);
        user.setIs_active(0);
        user.setNo_kartu(users.getNo_kartu());
        user.setCvv(users.getCvv());
        user.setNama_pemilik(users.getNama_pemilik());
        user.setExp_date(users.getExp_date());
        userRepository.save(user);

        emailService.sendMail(users.getEmail(), "Aktivasi akun", "Silahkan klik link aktivasi di bawah ini ");

        response.setStatus(true);
        response.setData(user);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }

    public UserResponse aktivasi(Integer id, UserRequest users){
        UserResponse response = new UserResponse();

        Users user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Data tidak ditemukan");
        });

        user.setIs_active(users.getIs_active());

        user = userRepository.save(user);

        
        response.setStatus(true);
        response.setData(user);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }

    public UserResponse cekStatus(UserRequest users){
        UserResponse response = new UserResponse();
        Map<String, Object> status = new HashMap<>();

        if(!userRepository.existsByEmail(users.getEmail())){
            status.put("status", "TIDAK TERDAFTAR");
            response.setStatus(true);
            response.setData(status);
            response.setError(null);
            response.setMessage("ok");
            return response;
        }

        Users user = userRepository.findUsersByEmail(users.getEmail());
       
        if(user.getIs_active() == 0){
            status.put("status", "BELUM TERVALIDASI");
        } else {
             status.put("status", "TERDAFTAR");
        }
        response.setStatus(true);
        response.setData(status);
        response.setError(null);
        response.setMessage("ok");
        return response;
    }

    public UserResponse authentication(UserRequest users){
        UserResponse response = new UserResponse();
        Map<String, Object> status = new HashMap<>();

        if(!userRepository.existsByEmail(users.getEmail())){
            response.setStatus(false);
            response.setData(null);
            response.setError(null);
            response.setMessage("Email salah");
            return response;
        }

        Users user = userRepository.findUsersByEmail(users.getEmail());
       
        if(user.getIs_active() == 1){
            

            if(BCrypt.checkpw(users.getPassword(), user.getPassword())){
                Calendar date = Calendar.getInstance();
                System.out.println("Current Date and TIme : " + date.getTime());
                long timeInSecs = date.getTimeInMillis();
                Date afterAdding10Mins = new Date(timeInSecs + (60 * 60 * 1000));
                System.out.println("After adding 10 mins : " + afterAdding10Mins);

               
                status.put("token", UUID.randomUUID().toString());
                status.put("exp_token", "After adding 60 mins : " + afterAdding10Mins);

           

                user.setToken(UUID.randomUUID().toString());
                user.setExp_token(afterAdding10Mins.getTime());
                user.setId(user.getId());
                userRepository.save(user);



                response.setStatus(true);
                response.setData(status);
                response.setError(null);
                response.setMessage("Sukses login");
                return response;
            } else {
                response.setStatus(false);
                response.setData(null);
                response.setError(null);
                response.setMessage("password salah");
                return response;
            }

        } else {
             response.setStatus(false);
                response.setData(null);
                response.setError(null);
                response.setMessage("Akun Anda belum divalidasi");
                return response;
        }
    }

    public UserResponse lupapassword(UserRequest users){
        UserResponse response = new UserResponse();

        emailService.sendMail(users.getEmail(), "Reset Password", "Silahkan klik link reset password di bawah ini ");

        response.setStatus(true);
        response.setData(null);
        response.setError(null);
        response.setMessage("Link reset password telag dikirm ke email Anda");
        return response;
    }

    public UserResponse resetpassword(Integer id, UserRequest users){
        UserResponse response = new UserResponse();

        Users user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Data tidak ditemukan");
        });

        String pwdhHash = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());

        user.setPassword(pwdhHash);

        user = userRepository.save(user);

        response.setStatus(true);
        response.setData(null);
        response.setError(null);
        response.setMessage("Password berhasil diubah");
        return response;
    }

    public UserResponse logout(Integer id){
        UserResponse response = new UserResponse();

        Users user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("data tidak ditemukan");
        });

        user.setToken(null);
        user.setExp_token(null);

        user = userRepository.save(user);
        
        response.setStatus(true);
        response.setData(null);
        response.setError(null);
        response.setMessage("Logout berhasil");
        return response;
    }

    public UserResponse updateProfile(UserRequest userRequest){
        UserResponse response = new UserResponse();

        Users user = userRepository.findById(userRequest.getId()).orElseThrow(() -> {
            throw new NoSuchElementException("Data tidak ditemukan");
        });

        user.setNama(userRequest.getNama());
        user.setNo_kartu(userRequest.getNo_kartu());
        user.setCvv(userRequest.getCvv());
        user.setExp_date(userRequest.getExp_date());
        user.setNama_pemilik(userRequest.getNama_pemilik());

        user = userRepository.save(user);
        response.setStatus(true);
        response.setData(null);
        response.setError(null);
        response.setMessage("Data berhasil diubah");
        return response;
    }
}
