package com.pipespace.pipespace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.pipespace.pipespace.authentication.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public UserDetails findUserByUsuario(String usuario);
}
