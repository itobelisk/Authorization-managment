package com.auth.server.entity.webuser.response;

import com.auth.server.entity.role.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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

    private List<String> roles;
}
