package az.mapacademy.announcement_backend.Controller;



import az.mapacademy.announcement_backend.Service.AuthService;
import az.mapacademy.announcement_backend.dto.*;
import az.mapacademy.announcement_backend.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);

        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        return baseResponse;
    }

    @PostMapping("sign-up")
    public BaseResponse<UserResponse> register(@RequestBody @Valid UserRequestRegister register) {
        var userResponse = authService.register(register);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}