package com.franklin.techblog.service.serviceimpl;

import com.franklin.techblog.Exception.UserAlreadyExistsException;
import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.repository.UserRepository;
import com.franklin.techblog.service.IUserService;
import com.franklin.techblog.mapper.UserMapper;
import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.security.PasswordHasher;
import com.franklin.techblog.security.PasswordVerifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordVerifier passwordVerifier;

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
    public boolean authenticateUser(String usernameOrEmail, String password) {
        Optional<BlogUser> optionalUsers = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (optionalUsers.isPresent()) {
            BlogUser blogUser = optionalUsers.get();
            return passwordVerifier.checkPassword(password, blogUser.getPassword());
        }
        return false;
    }
}
