package com.JWT.Token1.JWT.Token1.Service;


import com.JWT.Token1.JWT.Token1.dto.UserDto;
import com.JWT.Token1.JWT.Token1.loginVerify.LoginVerify;

public interface UserService {
    UserDto createUser(UserDto userDto);

    String loginVerify(LoginVerify loginVerify);
}
