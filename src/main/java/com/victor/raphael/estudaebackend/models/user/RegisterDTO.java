package com.victor.raphael.estudaebackend.models.user;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}
