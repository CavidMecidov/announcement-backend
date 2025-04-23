package az.mapacademy.announcement_backend.Service;

import az.mapacademy.announcement_backend.Service.JwtService;
import az.mapacademy.announcement_backend.dao.UserDao;
import az.mapacademy.announcement_backend.dto.LoginResponse;
import az.mapacademy.announcement_backend.dto.UserLoginRequest;
import az.mapacademy.announcement_backend.dto.UserRequestRegister;
import az.mapacademy.announcement_backend.dto.UserResponse;
import az.mapacademy.announcement_backend.entity.User;
import az.mapacademy.announcement_backend.exception.ConflictException;
import az.mapacademy.announcement_backend.exception.NotFoundException;
import az.mapacademy.announcement_backend.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));


        User user = userDao.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtService.generateAccessToken(user);

        return new LoginResponse(token);
    }

    public UserResponse register(UserRequestRegister request) {
        var user = userMapper.toUser(request);
        checkUsernameExists(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    private void checkUsernameExists(User user) {
        userDao.findByUsername(user.getUsername())
                .ifPresent(user1 ->
                {
                    throw new ConflictException("Username already exists");
                });
    }
}