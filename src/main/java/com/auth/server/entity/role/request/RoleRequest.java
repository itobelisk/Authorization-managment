package com.auth.server.entity.role.request;

import com.auth.server.entity.privelege.response.PrivilegeResponse;
import com.auth.server.entity.webuser.response.WebUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private Long id;
    private String name;
    private Collection<WebUserResponse> users;
    private Collection<PrivilegeResponse> privileges;
    private Collection<RoleRequest> roleRequestsCollection;
}
