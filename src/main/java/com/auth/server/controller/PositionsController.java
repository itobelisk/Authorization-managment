package com.auth.server.controller;

import com.auth.server.api.PositionsApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.Positions;
import com.auth.server.entity.position.request.PositionRequest;
import com.auth.server.entity.position.request.PositionUpdateRequest;
import com.auth.server.services.positions.impl.PositionsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PositionsController implements PositionsApi {
    private final PositionsServiceImpl positionsService;

    @Override
    public ResponseEntity<BaseResponse<?>> all() {
        Map<String, List<Positions>> responseMap = positionsService.all();
        BaseResponse<?> response = new BaseResponse<>(new Date(), true, HttpStatus.ACCEPTED, responseMap);
        return new ResponseEntity<>(response, response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> update(PositionUpdateRequest positionRequest) {
        log.info(positionRequest.getPositionUpdateList().iterator().next().getPositionName());
        log.info(String.valueOf(positionRequest.getPositionUpdateList().iterator().next().getId()));
        return null;
    }

}
