package isthatkirill.tasklist.web.controller;

import isthatkirill.tasklist.domain.user.User;
import isthatkirill.tasklist.service.AuthService;
import isthatkirill.tasklist.service.UserService;
import isthatkirill.tasklist.web.dto.auth.JwtRequest;
import isthatkirill.tasklist.web.dto.auth.JwtResponse;
import isthatkirill.tasklist.web.dto.user.UserDto;
import isthatkirill.tasklist.web.dto.validation.OnCreate;
import isthatkirill.tasklist.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest jwtRequest) {
        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
