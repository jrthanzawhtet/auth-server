package com.example.authserver.service;

import com.example.authserver.dao.OtpDao;
import com.example.authserver.dao.UserDao;
import com.example.authserver.entity.Otp;
import com.example.authserver.entity.User;
import com.example.authserver.util.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final OtpDao otpDao;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    public  boolean check(Otp otpToValidate){
        Optional<Otp> userOtp =
                otpDao.findOtpByUsername(otpToValidate.getUsername());
        if(userOtp.isPresent()){
            Otp otp = userOtp.get();
            if(otpToValidate.getCode().equals(otp.getCode())){
                return true;
            }
        }
        return false;
    }

    public UserService(OtpDao otpDao, PasswordEncoder passwordEncoder, UserDao userDao) {
        this.otpDao = otpDao;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
    public void auth(User user){
        Optional<User> o=userDao.findUserByUsername(user.getUsername());
        if(o.isPresent()){
            User user1=o.get();
            if(passwordEncoder.matches(user.getPassword()
                    ,user1.getPassword())){
                renewOtp(user1);
            }
            else {
                throw new BadCredentialsException("Bad Credentials!");
            }
        }
        else
            throw new BadCredentialsException("Username Not Found!");

    }
    private void renewOtp(User u){
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> userOtp = otpDao.findOtpByUsername(u.getUsername());
        if(userOtp.isPresent()){
            Otp otp=userOtp.get();
            otp.setCode(code);
        }
        else{
            Otp otp=new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpDao.save(otp);
        }
    }





}
