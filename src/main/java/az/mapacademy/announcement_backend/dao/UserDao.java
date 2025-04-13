package az.mapacademy.announcement_backend.dao;


import az.mapacademy.announcement_backend.entity.User;

import java.util.Optional;

public interface UserDao {
    User save(User user);


    Optional<User> findById(Long id);

    void delete(Long id);

    Optional<User> findByUsername(String username);

}