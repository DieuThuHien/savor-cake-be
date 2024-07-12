package org.example.service;

import org.example.dto.request.LoginRequest;

public interface AuthenticationService {
    String login(LoginRequest loginRequest);
}
