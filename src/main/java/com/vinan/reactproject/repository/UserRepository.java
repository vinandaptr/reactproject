package com.vinan.reactproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinan.reactproject.model.dto.UserDto;
import com.vinan.reactproject.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	UserDto save(UserDto userDto);
}
