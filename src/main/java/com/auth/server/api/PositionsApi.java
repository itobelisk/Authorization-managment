package com.auth.server.api;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.request.PositionRequest;
import com.auth.server.entity.position.request.PositionUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/positions")
public interface PositionsApi {

    @GetMapping("/all")
    ResponseEntity<BaseResponse<?>> all();

    @PutMapping("/update")
    ResponseEntity<BaseResponse<?>> update(@RequestBody PositionUpdateRequest positionRequest);
}
