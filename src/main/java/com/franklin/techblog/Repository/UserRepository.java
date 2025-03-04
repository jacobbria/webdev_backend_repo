package com.franklin.techblog.Repository;

import com.franklin.techblog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByMobileNumber(String mobileNumber);


}