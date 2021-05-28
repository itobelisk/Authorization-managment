package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/role/admin")
public interface RoleApi {

    @PostMapping("/save")
    ResponseEntity<BaseResponse<?>> save(@RequestHeader(name = "Cookie") String accessToken,
                                         @RequestBody RoleRequest roleRequest);

    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all();

    @PutMapping("/update")
    ResponseEntity<BaseResponse<?>> update(@RequestHeader(name = "Cookie") String accessToken,
                                           @RequestBody RoleRequest roleRequest);

    @DeleteMapping("/delete")
    ResponseEntity<BaseResponse<?>> delete(@RequestHeader(name = "Cookie") String accessToken,
                                           @RequestParam Long id);

    @PostMapping("/single")
    ResponseEntity<BaseResponse<?>> single(@RequestHeader(name = "Cookie") String accessToken,
                                           @RequestParam Long id);
}
