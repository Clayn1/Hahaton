package com.pivo.hahatonbe.model.dto;

import com.pivo.hahatonbe.model.entity.Group;
import com.pivo.hahatonbe.model.entity.University;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private Integer id;
    private String email;
    private String name;
    private String role;

    private Boolean isTeacher;

    public UserData(Integer id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
