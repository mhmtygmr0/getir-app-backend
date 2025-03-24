package com.getirApp.getirAppBackend.service.user;

import com.getirApp.getirAppBackend.core.exception.NotFoundException;
import com.getirApp.getirAppBackend.entity.User;
import com.getirApp.getirAppBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Kullanıcı bulunamadı: " + id));
    }

    @Override
    public List<User> getUserList() {
        return this.userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.get(id);
        this.userRepository.delete(user);
    }
}
