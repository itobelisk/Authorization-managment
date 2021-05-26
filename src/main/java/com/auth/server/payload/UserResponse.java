package com.auth.server.payload;

import com.auth.server.entity.webuser.response.WebUserResponse;
import com.auth.server.entity.webuser.response.WebUserShortResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private WebUserShortResponse webUser;
}
