package com.auth.server.entity.privelege.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrivilegeResponse {
    private Long id;
    private Long createdDate;
    private Long updatedDate;
    private String name;
}
