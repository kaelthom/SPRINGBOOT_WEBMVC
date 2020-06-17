package com.advancedjava.springbootmvc.repository;


import com.advancedjava.springbootmvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
