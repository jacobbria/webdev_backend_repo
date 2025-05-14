package com.franklin.techblog.repository;

import com.franklin.techblog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findByMobileNumber(String mobileNumber);
    Optional<BlogUser> findByEmail(String email);


}