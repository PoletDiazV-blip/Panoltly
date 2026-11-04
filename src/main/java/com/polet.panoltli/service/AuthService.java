package com.polet.panoltli.service;

import com.polet.panoltli.dto.LoginRequestDto;
import com.polet.panoltli.dto.LoginResponseDto;
import com.polet.panoltli.dto.RegisterRequestDto;
import com.polet.panoltli.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
}