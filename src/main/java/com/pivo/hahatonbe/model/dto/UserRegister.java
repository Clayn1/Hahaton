package com.pivo.hahatonbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegister {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String fbToken;
    private Integer groupId;
    private Integer universityId;
    private String role;
}
