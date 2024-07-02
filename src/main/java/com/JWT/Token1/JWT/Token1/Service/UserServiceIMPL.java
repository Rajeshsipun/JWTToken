package com.JWT.Token1.JWT.Token1.Service;


import com.JWT.Token1.JWT.Token1.dto.UserDto;
import com.JWT.Token1.JWT.Token1.entity.User;
import com.JWT.Token1.JWT.Token1.loginVerify.LoginVerify;
import com.JWT.Token1.JWT.Token1.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService{

    private UserRepository userRepository ;
    private  JWTService jwtService ;

    public UserServiceIMPL(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToEntity(userDto);
        User saveUser = userRepository.save(user);
        UserDto userDto1 = entityToDto(saveUser);
        return userDto1;
    }



    public User dtoToEntity(UserDto userDto ){
        User user= new User() ;
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    public UserDto entityToDto(User user ){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;
    }

    @Override
    public String loginVerify(LoginVerify loginVerify ) {
        Optional<User> byUsername =
                userRepository.findByUsername(loginVerify.getUsername());
        if (byUsername.isPresent()){
            User user = byUsername.get();
            if(  BCrypt.checkpw(loginVerify.getPassword(),user.getPassword())){
                String token = jwtService.generateJWTToken(user);
                return token;
            }
        }

        return null;
    }

}
