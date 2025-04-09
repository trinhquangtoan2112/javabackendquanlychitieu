package com.data.toan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.ChangePasswordDto;
import com.data.toan.dto.request.RegisterDto;
import com.data.toan.dto.request.UserDto;
import com.data.toan.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseData<?> registerAccount(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);

    }

    @PatchMapping("/updateInfo")
    public ResponseData<?> updateInfo(@RequestBody UserDto userDto) {
        return userService.updateInfo(userDto);

    }

    @PatchMapping("/change-password")
    public ResponseData<?> changePassword(@RequestBody ChangePasswordDto userDto) {
        return userService.changePassword(userDto);

    }
}
