package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/role")
public interface RoleApi {

    @PostMapping("/admin/save")
    ResponseEntity<BaseResponse<?>> save(@RequestHeader(name = "Authorization") String accessToken,
                                         @RequestBody RoleRequest roleRequest);


    @GetMapping("/admin/all")
    ResponseEntity<BaseResponse<?>> all(@RequestHeader(name = "Authorization") String accessToken);

}
