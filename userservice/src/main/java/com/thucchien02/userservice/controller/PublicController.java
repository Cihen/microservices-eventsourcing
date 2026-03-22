package com.thucchien02.userservice.controller;


import com.thucchien02.userservice.dto.LoginRequestDto;
import com.thucchien02.userservice.dto.identity.TokenExchangeResponse;
import com.thucchien02.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
public class PublicController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    ResponseEntity<TokenExchangeResponse> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }
}
