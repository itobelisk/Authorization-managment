package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/role/admin")
public interface RoleApi {

    @PostMapping("/save")
    ResponseEntity<BaseResponse<?>> save(@RequestBody RoleRequest roleRequest);

    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all();

    @PutMapping("/update")
    ResponseEntity<BaseResponse<?>> update(@RequestBody RoleRequest roleRequest);

    @DeleteMapping("/delete")
    ResponseEntity<BaseResponse<?>> delete(@RequestParam Long id);

    @PostMapping("/single")
    ResponseEntity<BaseResponse<?>> single(@RequestParam Long id);

    @PutMapping("/position/save")
    ResponseEntity<BaseResponse<?>> updatePositions(@RequestBody RoleRequest roleRequest);
}
