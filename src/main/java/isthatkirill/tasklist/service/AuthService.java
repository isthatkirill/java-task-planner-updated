package isthatkirill.tasklist.service;

import isthatkirill.tasklist.web.dto.auth.JwtRequest;
import isthatkirill.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}


