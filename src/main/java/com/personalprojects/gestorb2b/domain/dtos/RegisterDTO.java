package com.personalprojects.gestorb2b.domain.dtos;

import com.personalprojects.gestorb2b.domain.enums.UserRole;

public record RegisterDTO(
        String email,
        String password,
        String fullName,
        UserRole role
) {
}
