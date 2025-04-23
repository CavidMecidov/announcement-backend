package az.mapacademy.announcement_backend.Controller;

import az.mapacademy.announcement_backend.Service.AuthService;
import az.mapacademy.announcement_backend.dto.*;
import az.mapacademy.announcement_backend.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("my-information")
    public BaseResponse<UserResponse> getMyInformation() {
        var userResponse = userService.getMyInformation();

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }

    @PutMapping
    public BaseResponse<UserResponse> update(
            @RequestBody UserRequestUpdate update) {
        var userResponse = userService.update(update);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }

    @PatchMapping("{userId}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public BaseResponse<UserResponse> updateUserStatus(
            @PathVariable("userId") Long userId,
            @RequestBody UserUpdateStatusRequest request) {
        log.info("Update user status request is called");

        var userResponse = userService.updateUserStatus(userId, request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}