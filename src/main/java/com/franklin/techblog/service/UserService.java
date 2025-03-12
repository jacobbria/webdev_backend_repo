package com.franklin.techblog.service;

import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.entity.User;
import com.franklin.techblog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public User updateUser(Long id, User updatedUser) {
             return userRepository.findById(id)
                     .map(user -> {
                        user.setName(updatedUser.getName());
                        user.setEmail(updatedUser.getEmail());
                        user.setMobileNumber(updatedUser.getMobileNumber());
                        user.setRole(updatedUser.getRole());
                        user.setPassword(updatedUser.getPassword());
                        return userRepository.save(user);
                    })
                    .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Deleted user with ID: " + id;
        }
        return "User with ID: " + id + ", not found";
    }

}
