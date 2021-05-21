package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/role/admin")
public interface RoleApi {

    @PostMapping("/save")
    ResponseEntity<BaseResponse<?>> save(@RequestHeader(name = "Authorization") String accessToken,
                                         @RequestBody RoleRequest roleRequest);

    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all(@RequestHeader(name = "Authorization") String accessToken);

    @PutMapping("/update")
    ResponseEntity<BaseResponse<?>> update(@RequestHeader(name = "Authorization") String accessToken,
                                           @RequestBody RoleRequest roleRequest);

    @DeleteMapping("/delete")
    ResponseEntity<BaseResponse<?>> delete(@RequestHeader(name = "Authorization") String accessToken,
                                           @RequestParam Long  id);

    @GetMapping("/single")
    ResponseEntity<BaseResponse<?>> single(@RequestHeader(name = "Authorization") String accessToken,
                                           @RequestParam Long  id);
}
