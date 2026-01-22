package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserDTO;
import com.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.findAllUsers());
    }
}
