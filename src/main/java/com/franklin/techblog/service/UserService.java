package com.franklin.techblog.service;

import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<BlogUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<BlogUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public BlogUser updateUser(Long id, BlogUser updatedBlogUser) {
             return userRepository.findById(id)
                     .map(user -> {
                        user.setName(updatedBlogUser.getName());
                        user.setEmail(updatedBlogUser.getEmail());
                        user.setMobileNumber(updatedBlogUser.getMobileNumber());
                        user.setRole(updatedBlogUser.getRole());
                        user.setPassword(updatedBlogUser.getPassword());
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
