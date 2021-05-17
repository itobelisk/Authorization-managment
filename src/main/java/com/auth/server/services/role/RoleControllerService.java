package com.auth.server.services.role;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;

public interface RoleControllerService {
    BaseResponse<?> save(RoleRequest roleRequest);

    BaseResponse<?> all();

    BaseResponse<?> update(RoleRequest roleRequest);

    BaseResponse<?> delete(Long id);
}
