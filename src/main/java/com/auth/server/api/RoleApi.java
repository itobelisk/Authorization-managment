package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/role/admin")
public interface RoleApi {
    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all();

    @PostMapping("/save")
    ResponseEntity<BaseResponse<?>> updatePositions(@RequestBody RoleRequest roleRequest);
}
