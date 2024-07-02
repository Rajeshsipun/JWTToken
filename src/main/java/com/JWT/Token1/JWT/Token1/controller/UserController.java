package com.JWT.Token1.JWT.Token1.controller;


import com.JWT.Token1.JWT.Token1.Service.UserService;
import com.JWT.Token1.JWT.Token1.dto.UserDto;
import com.JWT.Token1.JWT.Token1.entity.User;
import com.JWT.Token1.JWT.Token1.loginVerify.JWTTokenDto;
import com.JWT.Token1.JWT.Token1.loginVerify.LoginVerify;
import com.JWT.Token1.JWT.Token1.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService ;
    private UserRepository userRepository ;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
//http://localhost:8080/api/v1/user/create
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto ){
        if (userRepository.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>("Exists Email ", HttpStatus.BAD_REQUEST);
        }if (userRepository.existsByUsername(userDto.getUsername())){
            return new ResponseEntity<>("Exists Username",HttpStatus.BAD_REQUEST);
        }
        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt()));
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/user/login
    @PostMapping("/login")
    public ResponseEntity<?>loginVerify(@RequestBody LoginVerify loginVerify){
        String token =  userService.loginVerify(loginVerify);
     if (token!=null){
         JWTTokenDto jwtTokenDto = new JWTTokenDto();
         jwtTokenDto.setToken(token);
         jwtTokenDto.setType("JWT Token");
         return new ResponseEntity<>(jwtTokenDto,HttpStatus.OK);
     }
     return new ResponseEntity<>("Invalid Token",HttpStatus.OK);
    }
}
