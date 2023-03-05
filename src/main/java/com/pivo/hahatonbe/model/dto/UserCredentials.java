package com.pivo.hahatonbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {
    private Integer id;
    private String email;
    private String password;
    private String role;
}
