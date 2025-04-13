package az.mapacademy.announcement_backend.Service;

import az.mapacademy.announcement_backend.Mapper.UserMapper;
import az.mapacademy.announcement_backend.dao.UserDao;
import az.mapacademy.announcement_backend.dto.UserRequestRegister;
import az.mapacademy.announcement_backend.dto.UserRequestUpdate;
import az.mapacademy.announcement_backend.dto.UserResponse;
import az.mapacademy.announcement_backend.entity.User;
import az.mapacademy.announcement_backend.exception.ConflictException;
import az.mapacademy.announcement_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;


    public UserResponse create(UserRequestRegister register) {
        var user = userMapper.toUser(register);
        checkUsernamExists(user);// usere cevirdi
        user = userDao.save(user);// usere elave etdi
        return userMapper.toResponse(user);//
    }

    public UserResponse update(Long id, UserRequestUpdate request) {
        var user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(request, user);
        user = userDao.save(user);
        return userMapper.toResponse(user);

    }

    private void checkUsernamExists(User user) {
        userDao.findByUsername(user.getUsername()).ifPresent(user1 -> {

            throw new ConflictException("Username already exception");
        });
    }

}
