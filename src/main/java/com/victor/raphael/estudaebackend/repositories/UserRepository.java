package com.victor.raphael.estudaebackend.repositories;

import com.victor.raphael.estudaebackend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByUsername(String username);

}
