package com.ecommerce.user.service.serviceImpl;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.repo.UserRepo;
import com.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return UserMapper.toUserDTOEntity(userRepo.save(UserMapper.toUserEntity(userDTO)));
    }

    @Override
    public UserDTO getUserById(String id) {
        return UserMapper.toUserDTOEntity(Objects.requireNonNull(userRepo.findById(id).orElse(null)));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepo.findAll().stream().map(UserMapper::toUserDTOEntity).toList();
    }
}
