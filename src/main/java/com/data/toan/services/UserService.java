package com.data.toan.services;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.ChangePasswordDto;
import com.data.toan.dto.request.RegisterDto;
import com.data.toan.dto.request.UserDto;

public interface UserService {
    ResponseData<?> register(RegisterDto registerDto);

    ResponseData<?> updateInfo(UserDto userDto);

    ResponseData<?> changePassword(ChangePasswordDto userDto);
}
