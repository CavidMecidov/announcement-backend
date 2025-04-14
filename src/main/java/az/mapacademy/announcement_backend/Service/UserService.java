package az.mapacademy.announcement_backend.Service;

import az.mapacademy.announcement_backend.dao.UserDao;
import az.mapacademy.announcement_backend.dto.UserResponse;
import az.mapacademy.announcement_backend.dto.UserRequestUpdate;
import az.mapacademy.announcement_backend.dto.UserUpdateStatusRequest;
import az.mapacademy.announcement_backend.entity.User;
import az.mapacademy.announcement_backend.exception.NotFoundException;
import az.mapacademy.announcement_backend.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserResponse update(UserRequestUpdate update) {
        var username = getUsername();
        var user = getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(update, user);
        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    public Optional<User> getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserResponse getMyInformation() {
        String username = getUsername();
        var user = getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    public UserResponse updateUserStatus(Long userId, UserUpdateStatusRequest request) {
        var user = userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setEnabled(request.status());
        user = userDao.save(user);
        return userMapper.toResponse(user);
    }

    private static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}