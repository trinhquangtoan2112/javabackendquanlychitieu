package com.data.toan.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.toan.dto.reponse.ResponeError;
import com.data.toan.dto.reponse.ResponseData;
import com.data.toan.dto.request.ChangePasswordDto;
import com.data.toan.dto.request.RegisterDto;
import com.data.toan.dto.request.UserDto;
import com.data.toan.exception.User.InvalidEmailFormatException;
import com.data.toan.exception.User.UserDontExits;
import com.data.toan.exception.User.UserMailException;
import com.data.toan.helper.UserHelper;
import com.data.toan.model.UserEntity;
import com.data.toan.repository.UserRepository;
import com.data.toan.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseData<?> register(RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getMail())) {
            log.warn("Email đã tồn tại: {}", registerDto.getMail());
            throw new UserMailException("Mail đã tồn tại");
        } else if (!UserHelper.isEmail(registerDto.getMail())) {
            log.warn("Email khác định dạng: {}", registerDto.getMail());
            throw new InvalidEmailFormatException("Mail không đúng định dạng");
        } else {
            try {
                UserEntity user = UserEntity.builder().email(registerDto.getMail())
                        .password(passwordEncoder.encode(registerDto.getPassword())).build();
                userRepository.save(user);
            } catch (Exception e) {
                return new ResponeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Không thành công");

            }

        }

        return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo tài khoản thành công");

    }

    @Override
    public ResponseData<?> updateInfo(UserDto userDto) {
        UserEntity user = userRepository.findById(userDto.getId()).orElseThrow(() -> {
            log.warn("Người dùng không tồn tại {}", userDto.getId());
            throw new UserDontExits("Người dùng không tồn tại");
        });
        if (userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()) {
            log.warn("Username không được để trống");
            throw new UserDontExits("Username không được để trống");
        }
        user.setUsername(userDto.getUsername());

        userRepository.save(user);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Cập nhập tài khoản thành công", userDto);
    }

    @Override
    public ResponseData<?> changePassword(ChangePasswordDto userDto) {
        UserEntity user = userRepository.findById(userDto.getId()).orElseThrow(() -> {
            log.warn("Người dùng không tồn tại {}", userDto.getId());
            throw new UserDontExits("Người dùng không tồn tại");
        });
        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            log.warn("password không được để trống");
            throw new UserDontExits("password không được để trống");
        }
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Cập nhập mật khẩu thành công");
    }
}
