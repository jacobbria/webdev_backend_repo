package com.franklin.techblog.repository;

import com.franklin.techblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobileNumber(String mobileNumber);
    Optional<User> findByUsernameOrEmail(String username, String email);


}