package com.blog.api.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username should be min 4 characters")
    private String name;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 4,max = 10, message = "Password should be minimum 4 characters and maximum 10 characters")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDTO> roles = new HashSet<>();

}
