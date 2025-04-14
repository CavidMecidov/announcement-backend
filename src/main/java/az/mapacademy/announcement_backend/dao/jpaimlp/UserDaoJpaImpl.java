package az.mapacademy.announcement_backend.dao.jpaimlp;

import az.mapacademy.announcement_backend.Repository.UserRepository;
import az.mapacademy.announcement_backend.dao.UserDao;
import az.mapacademy.announcement_backend.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoJpaImpl implements UserDao {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
   @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}

