package com.umiitkose.restservice.controller;

import com.umiitkose.restservice.mapper.UserMapper;
import com.umiitkose.restservice.model.User;
import com.umiitkose.restservice.model.dto.UserDTO;
import com.umiitkose.restservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "user",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserByIdWithQuery(@RequestParam int id) {
        User user = userService.getUserById(id);
        UserDTO userDto = UserMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByIdWithParam(@PathVariable int id) {
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @GetMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity saveUser(@Valid @RequestBody UserDTO request) {
        userService.saveUser(request);
        URI location = URI.create("/users/" + 2);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody UserDTO request) {
        userService.updateUser(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/test")
    public ResponseEntity deleteUserWithQuery(@PathVariable int id, @RequestParam String name) {
        if (!name.equals("umit"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("yetkisiz işlem");

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
