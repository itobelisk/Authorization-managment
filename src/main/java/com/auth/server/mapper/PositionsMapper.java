package com.auth.server.mapper;

import com.auth.server.entity.position.Positions;
import com.auth.server.entity.position.response.PositionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PositionsMapper {
    private final PositionCategoryMapper positionCategoryMapper;

    public List<PositionResponse> toResponse(List<Positions> all) {
        return all.stream()
                .map(e -> new PositionResponse(
                        positionCategoryMapper.toPositionCategoryResponse(e.getPositionsCategories()),
                        e.getId(),
                        e.getPositionName(),
                        e.getPositionIcon(),
                        e.getPositionDetails()
                ))
                .collect(Collectors.toList());
    }
}
