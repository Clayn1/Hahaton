package com.pivo.hahatonbe.model.enums;

public enum Roles {
    ROLE_STUDENT("ROLE_STUDENT"), ROLE_TEACHER("ROLE_TEACHER"), ROLE_UNIADMIN("ROLE_UNIADMIN"), ROLE_ADMIN("ROLE_ADMIN");

    private final String role;
    Roles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
