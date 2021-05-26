package com.auth.server.entity.webuser.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebUserShortResponse {
    private Long id;
    private String firstName;

    private String lastName;
    private String email;

    private String imageUrl = "https://";
    private String phoneNumber = "";

    private Collection<? extends GrantedAuthority> roles;
}
