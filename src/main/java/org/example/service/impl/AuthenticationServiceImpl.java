package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.request.LoginRequest;
import org.example.entity.Account;
import org.example.exception.UnauthorizedException;
import org.example.jwt.JwtService;
import org.example.repository.AuthenticationRepository;
import org.example.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {
    final AuthenticationRepository authenticationRepository;
    final JwtService jwtService;
    @Override
    public String login(LoginRequest loginRequest) {
        Account account = authenticationRepository.findAccountByUsernameAndPassword(loginRequest.getUsername(),
                loginRequest.getPassword()).orElseThrow(() -> new UnauthorizedException("Tài khoản không đúng"));

        return jwtService.generateToken(account);
    }
}
