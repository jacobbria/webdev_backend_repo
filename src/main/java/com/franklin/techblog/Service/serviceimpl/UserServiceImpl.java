package com.franklin.techblog.Service.serviceimpl;

import com.franklin.techblog.Exception.UserAlreadyExistsException;
import com.franklin.techblog.Repository.UserRepository;
import com.franklin.techblog.Service.IUserService;
import com.franklin.techblog.entity.Users;
import com.franklin.techblog.mapper.UserMapper;
import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.security.PasswordHasher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;


    @Override
    public void createAccount(UserDto userDto) {


            Users users = UserMapper.mapToCustomer(userDto, new Users());
            Optional<Users> optionalCustomer = userRepository.findByMobileNumber(userDto.getMobileNumber());
            if(optionalCustomer.isPresent()) {
                throw new UserAlreadyExistsException("Customer already registered with given mobileNumber "
                        +userDto.getMobileNumber());
            }
         String Password=users.getPassword();
             users.setPassword(PasswordHasher.hashPassword(Password));
            Users savedCustomer = userRepository.save(users);
       }





}
