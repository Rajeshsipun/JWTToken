package com.JWT.Token1.JWT.Token1.repository;


import com.JWT.Token1.JWT.Token1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

   Boolean existsByEmail(String email);
   Boolean existsByUsername(String Username);
}