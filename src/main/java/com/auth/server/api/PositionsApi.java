package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/positions")
public interface PositionsApi {

    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all();
}
