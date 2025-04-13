package az.mapacademy.announcement_backend.Controller;


import az.mapacademy.announcement_backend.Service.UserService;
import az.mapacademy.announcement_backend.dto.BaseResponse;
import az.mapacademy.announcement_backend.dto.UserRequestRegister;
import az.mapacademy.announcement_backend.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Bean create
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;

    @PostMapping("sign-up")
    public BaseResponse<UserResponse> register(@RequestBody@Valid UserRequestRegister register){
        var userResponse = userService.create(register);
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return  baseResponse;
    }



}
