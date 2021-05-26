package com.auth.server.payload;

import com.auth.server.entity.webuser.WebUser;
import com.auth.server.entity.webuser.response.WebUserResponse;
import com.auth.server.util.CookieUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private WebUserResponse webUser;
}
