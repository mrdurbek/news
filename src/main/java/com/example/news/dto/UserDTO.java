package com.example.news.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fullname;
    private String username;
    private String password;
    private Long lavozim_id;
    private boolean enabled;
}
