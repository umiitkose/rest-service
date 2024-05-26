package com.umiitkose.restservice.mapper;

import com.umiitkose.restservice.model.User;
import com.umiitkose.restservice.model.dto.UserDTO;

public class UserMapper {
    public static UserDTO toDto(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();

        return new UserDTO(name, surname, email);
    }

    public static User toUser(UserDTO userDTO) {
        return new User( userDTO.getName(), userDTO.getSurname(), userDTO.getEmail());
    }


}