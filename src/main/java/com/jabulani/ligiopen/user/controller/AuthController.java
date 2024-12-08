package com.jabulani.ligiopen.user.controller;

import com.jabulani.ligiopen.response.Response;
import com.jabulani.ligiopen.user.dto.LoginUserDto;
import com.jabulani.ligiopen.user.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<Response> register(RegisterUserDto userRegistrationDetails);
    ResponseEntity<Response> login(LoginUserDto userLoginDetails);
}
