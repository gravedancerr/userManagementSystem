package com.webservice.usermanagement.model;

import com.webservice.usermanagement.dto.EditUserDto;
import com.webservice.usermanagement.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String login;

    private String password;

    private Date birthday;

    private Role role;

    public User(UserDto userDto) {
        BeanUtils.copyProperties(userDto, this);
    }

    public User(EditUserDto editUserDto) {
        BeanUtils.copyProperties(editUserDto, this);
    }
}
