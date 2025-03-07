package com.franklin.techblog.Service.serviceimpl;

import com.franklin.techblog.Exception.UserAlreadyExistsException;
import com.franklin.techblog.Repository.UserRepository;
import com.franklin.techblog.Service.IUserService;
import com.franklin.techblog.entity.Users;
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
        Users users = UserMapper.mapToUser(userDto, new Users());
        Optional<Users> optionalCustomer = userRepository.findByMobileNumber(userDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with given mobileNumber "
                    + userDto.getMobileNumber());
        }

        String password = users.getPassword();
        users.setPassword(PasswordHasher.hashPassword(password));
        users.setUsername(users.getEmail().split("@")[0]);
        userRepository.save(users);
    }

    @Override
    public boolean authenticateUser(String usernameOrEmail, String password) {
        Optional<Users> optionalUsers = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();
            return passwordVerifier.checkPassword(password, user.getPassword());
        }
        return false;
    }
}
