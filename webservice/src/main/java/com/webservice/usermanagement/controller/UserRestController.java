package com.webservice.usermanagement.controller;

import com.webservice.usermanagement.dto.EditUserDto;
import com.webservice.usermanagement.dto.UserDto;
import com.webservice.usermanagement.model.User;
import com.webservice.usermanagement.service.UserService;
import com.webservice.usermanagement.utils.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> findAllUsers() {
        List<User> users = userService.findAll();
        if (users != null) {
            return users;
        } else {
            throw new UserNotFoundException("Could not find users");
        }
    }

    @GetMapping("/id/{id}")
    public User findUserById(@PathVariable String id) {
        User userById = userService.findById(id);
        if (userById != null) {
            return userById;
        } else {
            throw new UserNotFoundException("Could not find user by id");
        }
    }

    @GetMapping("/login/{login}")
    public User findByLogin(@PathVariable String login) {
        User userByLogin = userService.findByLogin(login);
        if (userByLogin != null) {
            return userByLogin;
        } else {
            throw new UserNotFoundException("Could not find user by login");
        }
    }

    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable String email) {
        User userByEmail = userService.findByEmail(email);
        if (userByEmail != null) {
            return userByEmail;
        } else {
            throw new UserNotFoundException("Could not find user by email");
        }
    }

    @PostMapping
    public User addUser(@RequestBody UserDto user,
            BindingResult bindingResult) {
        if (userService.findByLogin(user.getLogin()) != null) {
            ObjectError loginError = new ObjectError("login",
                    "User with such login already exists");
            bindingResult.addError(loginError);
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            ObjectError emailError = new ObjectError("email",
                    "User with such email already exists");
            bindingResult.addError(emailError);
        }
        if (!userService.checkBirthday(new User(user))) {
            ObjectError birthdayError = new ObjectError("birthday",
                    "Date of birth is invalid. Should be in past");
            bindingResult.addError(birthdayError);
        }
        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("User could not be created");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(new User(user));
        return userService.findByLogin(user.getLogin());
    }

    @PutMapping("/{id}")
    public User editUser(@RequestBody @Valid EditUserDto user,
            @PathVariable String id,
            BindingResult bindingResult) {
        if (!userService.checkBirthday(new User(user))) {
            ObjectError birthdayError = new ObjectError("birthday",
                    "Date of birth is invalid. Should be in past");
            bindingResult.addError(birthdayError);
        }
        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("User could not be updated");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(new User(user), id);
        return userService.findById(user.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.remove(userService.findById(id));
    }
}
