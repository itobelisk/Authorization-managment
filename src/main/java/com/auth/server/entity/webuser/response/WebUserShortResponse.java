package com.auth.server.entity.webuser.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
