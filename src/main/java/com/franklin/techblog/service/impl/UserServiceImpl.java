package com.franklin.techblog.service.impl;

import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.exception.UserAlreadyExistsException;
import com.franklin.techblog.mapper.UserMapper;
import com.franklin.techblog.repository.UserRepository;
import com.franklin.techblog.security.PasswordHasher;
import com.franklin.techblog.security.PasswordVerifier;
import com.franklin.techblog.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;

    @Override
    public void createAccount(UserDto userDto) {
        BlogUser blogUser = UserMapper.mapToUser(userDto, new BlogUser());
        Optional<BlogUser> optionalCustomer = userRepository.findByMobileNumber(userDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with given mobileNumber "
                    + userDto.getMobileNumber());
        }

        String password = blogUser.getPassword();
        blogUser.setPassword(PasswordHasher.hashPassword(password));
        blogUser.setUsername(blogUser.getEmail().split("@")[0]);
        userRepository.save(blogUser);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<BlogUser> optionalUsers = userRepository.findByEmail(email);

        if (optionalUsers.isPresent()) {
            BlogUser blogUser = optionalUsers.get();
            return PasswordVerifier.checkPassword(password, blogUser.getPassword());
        }
        return false;
    }

    @Override
    public List<BlogUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<BlogUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<BlogUser> getUserByEmail(String email) {return userRepository.findByEmail(email);}

    @Override
    public BlogUser updateUser(Long id, BlogUser updatedBlogUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedBlogUser.getName());
                    user.setEmail(updatedBlogUser.getEmail());
                    user.setMobileNumber(updatedBlogUser.getMobileNumber());
                    user.setRole(updatedBlogUser.getRole());
                    user.setPassword(PasswordHasher.hashPassword(updatedBlogUser.getPassword()));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Deleted user with ID: " + id;
        }
        return "User with ID: " + id + ", not found";
    }

}
