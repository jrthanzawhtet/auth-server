package com.example.authserver.dao;

import com.example.authserver.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpDao extends JpaRepository<Otp,String> {
    Optional<Otp> findOtpByUsername(String username);
}
