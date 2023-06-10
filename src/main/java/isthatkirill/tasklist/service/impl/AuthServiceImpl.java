package isthatkirill.tasklist.service.impl;

import isthatkirill.tasklist.service.AuthService;
import isthatkirill.tasklist.web.dto.auth.JwtRequest;
import isthatkirill.tasklist.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
