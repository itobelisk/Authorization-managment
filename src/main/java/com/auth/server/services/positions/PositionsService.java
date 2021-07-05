package com.auth.server.services.positions;

import com.auth.server.entity.position.response.PositionsResponse;

import java.util.List;
import java.util.Map;

public interface PositionsService {
    Map<String, List<PositionsResponse>>  all();
}

