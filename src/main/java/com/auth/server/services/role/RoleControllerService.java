package com.auth.server.services.role;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;

import javax.servlet.http.HttpServletRequest;

public interface RoleControllerService {
    BaseResponse<?> save(RoleRequest roleRequest, String accessToken);

    BaseResponse<?> all();

    BaseResponse<?> update(RoleRequest roleRequest, String accessToken);

    BaseResponse<?> delete(String accessToken, Long id);

    BaseResponse<?> single(String accessToken, Long id);
}
