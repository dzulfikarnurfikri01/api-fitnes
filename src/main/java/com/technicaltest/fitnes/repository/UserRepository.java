package com.technicaltest.fitnes.repository;

import org.springframework.data.repository.CrudRepository;

import com.technicaltest.fitnes.models.Users;


public interface UserRepository extends CrudRepository<Users, Integer> {

    Boolean existsByEmail(String email);

    Users findUsersByEmail(String email);

    // @Modifying
    // @Transactional
    // @Query("update users u set token = :token, exp_token = :exp where email = :email")
    // void updateTokenByEmail(@Param("token") String token, @Param("exp") Date exp, @Param("email") String email);

}