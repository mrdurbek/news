package com.example.news.controller;

import com.example.news.aop.CheckPermission;
import com.example.news.dto.LoginDTO;
import com.example.news.dto.RegisterDTO;
import com.example.news.dto.UserDTO;

import com.example.news.jwt.JwtProwider;
import com.example.news.response.ApiResponse;
import com.example.news.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    JwtProwider jwtProwider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @CheckPermission(permission = "ADD_USER")
    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse=authService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission(permission = "EDIT_USER")
    @PutMapping("/edituser/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        ApiResponse apiResponse=authService.editUser(id,userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @CheckPermission(permission = "DELETE_USER")
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        ApiResponse apiResponse=authService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            String token = jwtProwider.generateToken(loginDTO.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException b) {
            return ResponseEntity.status(409).body("Bunday user topilmadi");
        }
    }

}
