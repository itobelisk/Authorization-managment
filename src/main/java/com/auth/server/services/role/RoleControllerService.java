package com.auth.server.services.role;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.response.PositionsResponse;
import com.auth.server.entity.role.request.RoleRequest;

import java.util.List;
import java.util.Map;

public interface RoleControllerService {

    BaseResponse<?> manageCollection(RoleRequest roleRequest);
    Map<String, List<PositionsResponse>> all();
}
