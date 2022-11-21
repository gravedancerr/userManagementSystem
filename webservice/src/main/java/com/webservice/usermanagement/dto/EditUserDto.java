package com.webservice.usermanagement.dto;

import com.webservice.usermanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {
    private String id;

    @Size(min = 1, max = 70)
    private String firstName;

    @Size(min = 1, max = 70)
    private String lastName;

    @Size(min = 3, max = 70)
    private String email;

    @Size(min = 4, max = 70)
    private String login;

    @Size(min = 4, max = 70)
    private String password;

    @Past
    private Date birthday;

    private Role role;
}
