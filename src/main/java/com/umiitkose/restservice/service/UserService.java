package com.umiitkose.restservice.service;


import com.umiitkose.restservice.exception.UserNotFoundException;
import com.umiitkose.restservice.mapper.UserMapper;
import com.umiitkose.restservice.model.User;
import com.umiitkose.restservice.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    List<User> userList = new ArrayList<>();

    public User getUserById(int id) {
        if (id < 0 || id >= userList.size())
            throw new UserNotFoundException();

        return userList.get(id);
    }

    public List<UserDTO> getAllUser() {
        return userList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public void saveUser(UserDTO request) {
        User user = UserMapper.toUser(request);
        userList.add(user);
    }

    public void updateUser(int id, UserDTO request) {
        User user = userList.get(id);
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
    }

    public void deleteUser(int id) {
        userList.remove(id);
    }


    {
        User user = new User();
        user.setName("Ümit");
        user.setSurname("KÖSE");
        user.setEmail("umiitkose@gmail.com");

        User user1 = new User();
        user1.setName("Umit1");
        user1.setSurname("KOSE1");
        user1.setEmail("umiitkose@gmail.com");

        userList.add(user);
        userList.add(user1);
    }

}
