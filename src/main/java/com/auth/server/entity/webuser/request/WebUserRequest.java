package com.auth.server.entity.webuser.request;

import com.auth.server.annotations.PasswordPolicy;
import com.auth.server.entity.role.Role;
import com.auth.server.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebUserRequest {
    private String firstName;

    private String lastName;

    private String name = "";
    @Email
    private String email;

    private String imageUrl = "https://";

    private Boolean emailVerified = false;
    @PasswordPolicy
    private String password;
    @PasswordPolicy
    private String oldPassword;

    private AuthProvider provider = AuthProvider.local;

    private String providerId = "";

    private Boolean phoneNumberVerified = false;

    private String phoneNumber = "";

    private Boolean enable = true;

    private Collection<Role> roles;
}
