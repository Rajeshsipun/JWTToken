package com.JWT.Token1.JWT.Token1.repository;


import com.JWT.Token1.JWT.Token1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   Boolean existsByEmail(String email);
   Boolean existsByUsername(String Username);

 Optional<User> findByUsername(String Username);
}