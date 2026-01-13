package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserDTO;

import java.util.List;

public interface UserService {
   UserDTO addUser(UserDTO userDTO);
   UserDTO getUserById(String id);
   List<UserDTO> findAllUsers();
}
