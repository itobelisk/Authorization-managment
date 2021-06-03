package com.auth.server.entity.webuser.response;

import com.auth.server.entity.role.Role;
import com.auth.server.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebUserResponse {
    private Long id;
    private String firstName;

    private String lastName;

    private String username = "";

    private String email;

    private String imageUrl = "https://";

    private Boolean emailVerified = false;

    @Transient
    private String password;

    private AuthProvider provider = AuthProvider.local;

    private String providerId = "";

    private Boolean phoneNumberVerified = false;

    private String phoneNumber = "";

    private Boolean enable = true;

    private List<String> roles;
}
