package com.auth.server.entity.webuser.request;

import com.auth.server.entity.role.Role;
import com.auth.server.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String email;

    private String imageUrl = "https://";

    private Boolean emailVerified = false;

    private String password;

    private AuthProvider provider = AuthProvider.local;

    private String providerId = "";

    private Boolean phoneNumberVerified = false;

    private String phoneNumber = "";

    private Boolean enable = true;

    private Collection<Role> roles;
}
