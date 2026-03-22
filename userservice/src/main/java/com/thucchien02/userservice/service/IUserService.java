package com.thucchien02.userservice.service;

import com.thucchien02.userservice.dto.CreateUserRequestDTO;
import com.thucchien02.userservice.dto.LoginRequestDto;
import com.thucchien02.userservice.dto.UserResponseDTO;
import com.thucchien02.userservice.dto.identity.TokenExchangeResponse;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO dto);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, CreateUserRequestDTO dto);
    void deleteUser(Long id);

    TokenExchangeResponse login(LoginRequestDto dto);
}
