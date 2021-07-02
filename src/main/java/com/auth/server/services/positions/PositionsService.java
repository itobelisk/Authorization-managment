package com.auth.server.services.positions;

import com.auth.server.entity.position.Positions;

import java.util.List;
import java.util.Map;

public interface PositionsService {
    Map<String, List<Positions>>  all();
}

