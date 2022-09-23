package com.example.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotNull
    private String fullname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String prePassword;
}
